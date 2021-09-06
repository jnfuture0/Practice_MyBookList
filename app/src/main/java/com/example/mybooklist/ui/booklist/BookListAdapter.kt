package com.example.mybooklist.ui.booklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mybooklist.databinding.RecyclerviewItemBinding
import com.example.mybooklist.domain.model.BookInfoDomain

class BookListAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<BookInfoDomain, BookListAdapter.BookListViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        return BookListViewHolder(RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        val bookInfo = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(bookInfo)
        }
        holder.bind(bookInfo)
    }


    class BookListViewHolder(private var binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(bookInfo: BookInfoDomain) {
            binding.bookInfo = bookInfo
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<BookInfoDomain>() {
        override fun areItemsTheSame(oldItem: BookInfoDomain, newItem: BookInfoDomain): Boolean {
            //원래대로면 모든 값 비교해야하지만, isbn이 유니크한 값이므로 간단하게 한 것.
            //아이템이 같은지 비교해서 같다면 그대로 사용하는.
            return oldItem.title == newItem.title && oldItem.isbn == newItem.isbn
        }

        override fun areContentsTheSame(oldItem: BookInfoDomain, newItem: BookInfoDomain): Boolean {
            return oldItem.title == newItem.title
        }
    }

    class OnClickListener(val clickListener: (bookInfo: BookInfoDomain) -> Unit) {
        fun onClick(bookInfo: BookInfoDomain) = clickListener(bookInfo)
    }

}
