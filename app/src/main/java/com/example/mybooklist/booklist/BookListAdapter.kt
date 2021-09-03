package com.example.mybooklist.booklist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mybooklist.bindRecyclerView
import com.example.mybooklist.databinding.RecyclerviewItemBinding
import com.example.mybooklist.network.BookInfo

class BookListAdapter(val onClickListener: OnClickListener):
    ListAdapter<BookInfo, BookListAdapter.BookListViewHolder>(DiffCallback){

    private lateinit var mRecyclerView: RecyclerView
    var mItemCount = -1

    class BookListViewHolder(private var binding:RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bookInfo:BookInfo){
            binding.bookInfo = bookInfo
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<BookInfo>(){
        override fun areItemsTheSame(oldItem: BookInfo, newItem: BookInfo): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: BookInfo, newItem: BookInfo): Boolean {
            return oldItem.title == newItem.title
        }
    }

    override fun getItemCount(): Int {
        if(mItemCount != super.getItemCount()) {
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

    class OnClickListener(val clickListener: (bookInfo:BookInfo) -> Unit){
        fun onClick(bookInfo:BookInfo) = clickListener(bookInfo)
    }

}
