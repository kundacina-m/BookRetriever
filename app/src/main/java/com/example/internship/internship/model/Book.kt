package com.example.internship.internship.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "idBook")
        var id: Int = 0,

        @ColumnInfo(name = "title")
        var title: String = "",

        @ColumnInfo(name = "description")
        var description: String = "",

        @ColumnInfo(name = "urlToImage")
        var urlToImage: String? = null)