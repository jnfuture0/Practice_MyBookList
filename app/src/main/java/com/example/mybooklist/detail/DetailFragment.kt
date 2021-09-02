package com.example.mybooklist.detail

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mybooklist.booklist.BookListFragment
import com.example.mybooklist.databinding.FragmentDetailBinding

class DetailFragment:Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        @Suppress("UNUSED_VARIABLE")
        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val bookInfo = DetailFragmentArgs.fromBundle(requireArguments()).selectedBook
        val viewModelFactory = DetailViewModelFactory(bookInfo, application)

        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        return binding.root
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(android.R.id.home)
//        return super.onOptionsItemSelected(item)
//    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//    }
}