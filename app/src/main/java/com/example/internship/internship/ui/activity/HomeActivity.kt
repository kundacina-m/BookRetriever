package com.example.internship.internship.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.internship.internship.App
import com.example.internship.internship.R
import com.example.internship.internship.adapter.BooksAdapter
import com.example.internship.internship.model.Book
import com.example.internship.internship.ui.BooksListViewModel
import com.example.internship.internship.ui.MvvmActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeActivity : MvvmActivity(),BooksAdapter.OnItemClickListener {

    private var booksRecyclerView: RecyclerView? = null
    private var booksAdapter: BooksAdapter? = null

    private var bookListViewModel: BooksListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        booksRecyclerView = findViewById(R.id.fullList)
        booksAdapter = BooksAdapter(arrayListOf(), this)

        booksRecyclerView!!.layoutManager = LinearLayoutManager(this)
        booksRecyclerView!!.adapter = booksAdapter

        bookListViewModel = App.injectBookListViewModel()

        subscribe(bookListViewModel!!.getBooks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    showBooks(it)
                }, {
                    Log.d("activity",it.stackTrace.toString())
                }))

    }

    private fun showBooks(data: List<Book>) {
        if (!data.isEmpty())
       booksAdapter!!.addBooks(data)
    }

    override fun onItemClick(book: Book) {
        var intent = Intent(applicationContext, BookDetailsActivity::class.java)
        intent.putExtra("idBook", book.id)
        startActivity(intent)
    }
}
