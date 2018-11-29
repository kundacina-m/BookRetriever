package com.example.internship.internship.repository

import android.annotation.SuppressLint
import android.util.Log
import com.example.internship.internship.model.Book
import com.example.internship.internship.repository.api.BookApi
import com.example.internship.internship.repository.db.BookDao
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class BookRepository(private val bookApi: BookApi, private val bookDao: BookDao) {

    fun getBooks(): Observable<List<Book>> {
        return Observable.concatArray(
                getBooksFromDb(),
                getBooksFromApi())
    }

    fun getBookByIdFromDb(id: Int): Book{
            return bookDao.getBookById(id)

    }

    private fun getBooksFromDb(): Observable<List<Book>> {
        return bookDao.getBooks().filter { it.isNotEmpty() }
                .toObservable()
                .doOnNext {
                    Log.d("repo", "Dispatching ${it.size} books from DB...")
                }
    }

    private fun getBooksFromApi(): Observable<List<Book>> {
        return bookApi.getBooks()
                .doOnNext {
                    bookDao.deleteAllBooks()
                    Log.d("repo", "Dispatching ${it.size} books from API...")
                    storeBooksInDb(it)
                }
    }

    @SuppressLint("CheckResult")
    fun storeBooksInDb(books: List<Book>) {
        Observable.fromCallable { bookDao.insertAll(books) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Log.d("repo", "Inserted ${books.size} books from API in DB...")
                }
    }

}
