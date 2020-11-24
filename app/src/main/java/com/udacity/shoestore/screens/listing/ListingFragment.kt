package com.udacity.shoestore.screens.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ListingFragmentBinding
import kotlinx.android.synthetic.*

class ListingFragment : Fragment() {

    private lateinit var viewModel: ListViewModel

    private lateinit var binding: ListingFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.listing_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        binding.listViewModel = viewModel
        binding.setLifecycleOwner(this)

        // Set up LiveData observation relationship
        viewModel.newShoe.observe(viewLifecycleOwner, Observer { shoe ->
            shoe
        })

        // Floating action button listener
        binding.newShoeButton.setOnClickListener {
            findNavController().navigate(ListingFragmentDirections.actionListingToDetails())
        }

        return binding.root
    }

    private fun shoeAdded() {

    }
}