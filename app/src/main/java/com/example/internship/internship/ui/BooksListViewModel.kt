package com.example.internship.internship.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.example.internship.internship.db.BooksDatabase
import com.example.internship.internship.model.Books

class BooksListViewModel(application: Application) : AndroidViewModel(application) {

    private var listBooks: LiveData<List<Books>>
    private val appDb: BooksDatabase

    init {
        appDb = BooksDatabase.getDataBase(this.getApplication())
        listBooks = appDb.booksDao().getBooks()
    }

    fun getListBooks(): LiveData<List<Books>> {
        return listBooks
    }

    fun addBook(book: Books) {
        AddAsyncTask(appDb).execute(book)
    }


    class AddAsyncTask(db: BooksDatabase) : AsyncTask<Books, Void, Void>() {
        private var bookDb = db
        override fun doInBackground(vararg params: Books): Void? {
            bookDb.booksDao().insertBook(params[0])
            return null
        }

    }

}