package com.example.mybooklist.booklist

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mybooklist.MainActivity
import com.example.mybooklist.R
import com.example.mybooklist.databinding.FragmentBooklistBinding


class BookListFragment:Fragment() {

    private val viewModel:BookListViewModel by lazy {
        ViewModelProvider(this).get(BookListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val mDividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        val binding = FragmentBooklistBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.booksRecyclerView.adapter = BookListAdapter(BookListAdapter.OnClickListener{
            viewModel.displayBookDetails(it)
        })
        binding.booksRecyclerView.addItemDecoration(mDividerItemDecoration)
        binding.booksRecyclerView.addItemDecoration(VerticalItemDecorator(40))


        viewModel.navigateToSelectedBook.observe(viewLifecycleOwner, Observer {
            if(null!=it){
                this.findNavController().navigate(BookListFragmentDirections.actionShowDetail(it))
                viewModel.displayBookDetailsComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)

        val queryTextListener = object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchWithQuery(query)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        }

        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.menu_search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            setOnQueryTextListener(queryTextListener)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }
}