package com.example.internship.internship.repository.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.internship.internship.model.Book

@Database(entities = [(Book::class)], version = 1)
abstract class BooksDatabase : RoomDatabase() {

    /**
     * Get books DAO
     */
    abstract fun booksDao(): BookDao
}