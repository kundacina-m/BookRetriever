package com.example.internship.internship.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
        @PrimaryKey
        @ColumnInfo(name = "title")
        var title: String = "",

        @ColumnInfo(name = "description")
        var description: String = "",

        @ColumnInfo(name = "image")
        var image: String? = null)