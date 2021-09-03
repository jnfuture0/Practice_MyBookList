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
        binding.lifecycleOwner = viewLifecycleOwner



        val bookInfo = DetailFragmentArgs.fromBundle(requireArguments()).selectedBook
        val viewModelFactory = DetailViewModelFactory(bookInfo, application)

        val viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
        //viewModel.setBookInfo(bookInfo)
        binding.viewModel = viewModel
        


        return binding.root
    }

}