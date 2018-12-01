package com.example.internship.internship.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.example.internship.internship.utils.App
import com.example.internship.internship.R
import com.example.internship.internship.adapter.BooksAdapter
import com.example.internship.internship.model.Book
import com.example.internship.internship.ui.BooksListViewModel
import com.example.internship.internship.ui.MvvmActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.empty_layout.*

class HomeActivity : MvvmActivity(), BooksAdapter.OnItemClickListener {

    private lateinit var booksRecyclerView: RecyclerView
    private lateinit var booksAdapter: BooksAdapter
    private lateinit var bookListViewModel: BooksListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        booksRecyclerView = findViewById(R.id.fullList)
        booksAdapter = BooksAdapter(arrayListOf(), this)

        booksRecyclerView.layoutManager = LinearLayoutManager(this)
        booksRecyclerView.adapter = booksAdapter

        bookListViewModel = App.injectBookListViewModel()

        fetchBooks()

        refresh.setOnClickListener {
            fetchBooks()
        }
    }


    private fun showBooks(data: List<Book>) {
        if (!data.isEmpty()) {
            empty_view.visibility = View.GONE
            booksAdapter.addBooks(data)
        } else if (booksAdapter.itemCount == 0) empty_view.visibility = View.VISIBLE
    }

    private fun fetchBooks() {
        subscribe(bookListViewModel.getBooks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    showBooks(it)
                }, {
                    Log.d("activity", it.toString())
                }))
    }

    // Handle click on item from RecyclerView
    override fun onItemClick(book: Book) {
        var intent = Intent(this, BookDetailsActivity::class.java)
        intent.putExtra("bookTitle", book.title)
        startActivity(intent)
    }
}
