package com.example.mybooklist.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.mybooklist.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private val navArgs by navArgs<DetailFragmentArgs>()

    private val viewModel by viewModels<DetailViewModel> {
        DetailViewModelFactory(navArgs.selectedBook)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        @Suppress("UNUSED_VARIABLE")
        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner


//        val bookInfo = DetailFragmentArgs.fromBundle(requireArguments()).selectedBook
//        val viewModelFactory = DetailViewModelFactory(bookInfo)

        //val viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
        //viewModel.setBookInfo(bookInfo)
        binding.viewModel = viewModel


        return binding.root
    }

}