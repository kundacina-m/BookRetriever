package com.example.internship.internship.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.internship.internship.utils.App
import com.example.internship.internship.R
import com.example.internship.internship.ui.BooksListViewModel
import kotlinx.android.synthetic.main.activity_bookdetails.*
import org.jetbrains.anko.doAsyncResult
import org.jetbrains.anko.uiThread

class BookDetailsActivity: AppCompatActivity() {

    private lateinit var  bookListViewModel: BooksListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookdetails)

        bookListViewModel = App.injectBookListViewModel()
        val title = intent.getStringExtra("bookTitle")

        loadBookDetails(title)
    }

    private fun loadBookDetails(title: String){
        doAsyncResult {
            val chosenBook = bookListViewModel.getBookByTitle(title)
            uiThread {
                tvTitle.text = chosenBook.title
                tvDesc.text = chosenBook.description
                val imageView = ImageView(applicationContext)
                Glide.with(applicationContext).load(chosenBook.image).into(imageView)
                imgFrame.addView(imageView)
            }
        }
    }

}
