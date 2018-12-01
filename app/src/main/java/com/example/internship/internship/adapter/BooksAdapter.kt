package com.example.internship.internship.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.internship.internship.R
import com.example.internship.internship.model.Book

class BooksAdapter(books: ArrayList<Book>, listener: OnItemClickListener) : RecyclerView.Adapter<BooksAdapter.RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentBook: Book = listBooks[position]

        val titleBook = currentBook.title
        val descriptionBook = currentBook.description

        holder.mTitle.text = titleBook
        holder.mDescription.text = descriptionBook

        holder.bind(currentBook, listenerBooks)
    }

    private var listBooks: List<Book> = books

    private var listenerBooks: OnItemClickListener = listener

    interface OnItemClickListener {
        fun onItemClick(book: Book)
    }


    override fun getItemCount(): Int {
        return listBooks.size
    }

    fun addBooks(listBooks: List<Book>) {
        this.listBooks = listBooks
        notifyDataSetChanged()
    }


    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTitle = itemView.findViewById<TextView>(R.id.tvTitle)!!
        var mDescription = itemView.findViewById<TextView>(R.id.tvDesc)!!

        fun bind(book: Book, listener: OnItemClickListener) {
            itemView.setOnClickListener {
                listener.onItemClick(book)
            }
        }

    }
}