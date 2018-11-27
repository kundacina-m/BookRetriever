package com.example.internship.internship.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.internship.internship.model.Books

@Dao
interface BooksDao {

    /**
     * Insert books into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book: Books)

    /**
     * Get all the books from database
     */
    @Query("SELECT * FROM books")
    fun getBooks(): LiveData<List<Books>>
}