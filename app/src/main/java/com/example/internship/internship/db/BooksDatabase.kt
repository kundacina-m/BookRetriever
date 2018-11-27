package com.example.internship.internship.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.internship.internship.model.Books

@Database(entities = [(Books::class)], version = 1, exportSchema = false)
abstract class BooksDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: BooksDatabase? = null
        fun getDataBase(context: Context): BooksDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, BooksDatabase::class.java, "books-db")
                        .allowMainThreadQueries().build()
            }
            return INSTANCE as BooksDatabase
        }
    }

    /**
     * Get books DAO
     */
    abstract fun booksDao(): BooksDao
}