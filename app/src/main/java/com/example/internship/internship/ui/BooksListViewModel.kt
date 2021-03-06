package com.example.internship.internship.ui

import com.example.internship.internship.model.Book
import com.example.internship.internship.repository.BookRepository
import io.reactivex.Observable


class BooksListViewModel(private val bookRepository: BookRepository) {

    fun getBooks(): Observable<List<Book>> {
        return bookRepository.getBooks()
                .map { it }
                .onErrorReturn { emptyList() }
    }

    fun getBookByTitle(title: String): Book{
        return bookRepository.getBookByTitle(title)
    }

}