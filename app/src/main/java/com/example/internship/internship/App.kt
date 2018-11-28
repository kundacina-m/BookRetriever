package com.example.internship.internship

import android.app.Application
import android.arch.persistence.room.Room
import com.example.internship.internship.repository.BookRepository
import com.example.internship.internship.repository.api.BookApi
import com.example.internship.internship.repository.db.BooksDatabase
import com.example.internship.internship.ui.BooksListViewModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    companion object {
        private lateinit var retrofit: Retrofit
        private lateinit var bookApi: BookApi
        private lateinit var bookRepository: BookRepository
        private lateinit var bookListViewModel: BooksListViewModel
        private lateinit var bookDatabase: BooksDatabase

        fun injectBookApi() = bookApi

        fun injectBookListViewModel() = bookListViewModel

        fun injectBookDao() = bookDatabase.booksDao()
    }

    override fun onCreate() {
        super.onCreate()

        retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://raw.githubusercontent.com/")
                .build()

        bookApi = retrofit.create(BookApi::class.java)
        bookDatabase = Room.databaseBuilder(applicationContext,
                BooksDatabase::class.java, "books-db").allowMainThreadQueries().build()

        bookRepository = BookRepository(bookApi, bookDatabase.booksDao())
        bookListViewModel = BooksListViewModel(bookRepository)
    }
}
