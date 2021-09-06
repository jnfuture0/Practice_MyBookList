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

    private lateinit var mRecyclerView: RecyclerView
    var mItemCount = -1

    class BookListViewHolder(private var binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(bookInfo: BookInfoDomain) {
            binding.bookInfo = bookInfo
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<BookInfoDomain>() {
        override fun areItemsTheSame(oldItem: BookInfoDomain, newItem: BookInfoDomain): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: BookInfoDomain, newItem: BookInfoDomain): Boolean {
            return oldItem.title == newItem.title
        }
    }

    override fun getItemCount(): Int {
        if (mItemCount != super.getItemCount()) {
            mItemCount = super.getItemCount()
            if (mItemCount % 10 == 0) {
                when (mItemCount) {
                    10 -> mRecyclerView.scrollToPosition(0)
                    0 -> {
                    }
                    else -> {
                        mRecyclerView.scrollToPosition(mItemCount - 11)
                    }
                }
            }
        }
        return super.getItemCount()
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }


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

    class OnClickListener(val clickListener: (bookInfo: BookInfoDomain) -> Unit) {
        fun onClick(bookInfo: BookInfoDomain) = clickListener(bookInfo)
    }

}
