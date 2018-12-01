package com.example.internship.internship.repository.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.internship.internship.model.Book
import io.reactivex.Single

@Dao
interface BookDao {

    /**
     * Insert books into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(books: List<Book>)

    /**
     * Get all the books from database
     */
    @Query("SELECT * FROM books")
    fun getBooks(): Single<List<Book>>

    /**
     * Delete all the books from database
     */
    @Query("DELETE FROM books")
    fun deleteAllBooks()

    /**
     * Get book by title
     */
    @Query("SELECT * FROM books where title in (:title)")
    fun getBookByTitle(title: String): Book

}