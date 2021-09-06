package com.example.mybooklist.ui.booklist

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mybooklist.R
import com.example.mybooklist.databinding.FragmentBooklistBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookListFragment : Fragment() {

    private val viewModel by viewModels<BookListViewModel>()

    private lateinit var moveOutAnim: Animation
    private lateinit var moveInAnim: Animation


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBooklistBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setRecyclerView(binding.booksRecyclerView)

        moveInAnim = AnimationUtils.loadAnimation(context, R.anim.move_bottom_up)
        moveOutAnim = AnimationUtils.loadAnimation(context, R.anim.move_bottom_down)
        viewModel.isError.observe(viewLifecycleOwner) {
            if (it) {
                if (binding.booksErrorTextview.visibility != View.VISIBLE) {
                    binding.booksErrorTextview.visibility = View.VISIBLE
                    binding.booksErrorTextview.startAnimation(moveInAnim)
                }
            } else {
                if (binding.booksErrorTextview.visibility != View.GONE) {
                    binding.booksErrorTextview.startAnimation(moveOutAnim)
                    binding.booksErrorTextview.visibility = View.GONE
                }
            }
        }
        viewModel.navigateToSelectedBook.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(BookListFragmentDirections.actionShowDetail(it))
                viewModel.displayBookDetailsComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    private fun setRecyclerView(recyclerView: RecyclerView) {
        val mDividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        recyclerView.adapter = BookListAdapter(BookListAdapter.OnClickListener {
            viewModel.displayBookDetails(it)
        })
        recyclerView.addItemDecoration(mDividerItemDecoration)
        recyclerView.addItemDecoration(VerticalItemDecorator(40))
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.getMoreBooks()
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)

        val queryTextListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
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