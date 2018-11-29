package com.example.internship.internship.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.internship.internship.App
import com.example.internship.internship.model.Book
import com.example.internship.internship.ui.BooksListViewModel
import kotlinx.android.synthetic.main.activity_bookdetails.*

class BookDetailsActivity: AppCompatActivity() {

    private var bookListViewModel: BooksListViewModel? = null
    private var chosenBook: Book? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        bookListViewModel = App.injectBookListViewModel()
//
//        val neededId:Int = intent.getIntExtra("idBook",-1)
//
//        chosenBook = bookListViewModel!!.getBookById(neededId)
//
//        if (neededId != -1){
//            chosenBook = bookListViewModel!!.getBookById(neededId)
//            tvTitle.text = chosenBook!!.title
//            tvDesc.text = chosenBook!!.description
//        }

    }
}