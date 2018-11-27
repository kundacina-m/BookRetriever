package com.example.internship.internship.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.internship.internship.R
import com.example.internship.internship.adapter.BooksAdapter
import com.example.internship.internship.db.BooksDatabase
import com.example.internship.internship.model.Books
import com.example.internship.internship.ui.BooksListViewModel

class HomeActivity : AppCompatActivity(),BooksAdapter.OnItemClickListener {

    private var booksRecyclerView: RecyclerView? = null
    private var booksAdapter: BooksAdapter? = null

    private var viewModel: BooksListViewModel? = null

    private var db: BooksDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        db = BooksDatabase.getDataBase(this)

        booksRecyclerView = findViewById(R.id.fullList)
        booksAdapter = BooksAdapter(arrayListOf(), this)

        booksRecyclerView!!.layoutManager = LinearLayoutManager(this)
        booksRecyclerView!!.adapter = booksAdapter

        viewModel = ViewModelProviders.of(this).get(BooksListViewModel::class.java)

        viewModel!!.getListBooks().observe(this, Observer { books ->
            booksAdapter!!.addBooks(books!!)
        })
    }

    override fun onItemClick(book: Books) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
