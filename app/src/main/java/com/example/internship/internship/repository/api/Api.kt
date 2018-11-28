package com.example.internship.internship.repository.api

import com.example.internship.internship.model.Book
import io.reactivex.Observable
import retrofit2.http.GET

interface BookApi {

    // URL path
    @GET("danieloskarsson/mobile-coding-exercise/master/items.json")
    fun getBooks(): Observable<List<Book>>
}