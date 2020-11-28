package com.udacity.shoestore.screens.listing

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ListingFragmentBinding
import com.udacity.shoestore.databinding.ShoeViewBinding
import com.udacity.shoestore.models.Shoe

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

        //binding.listViewModel = viewModel
        binding.setLifecycleOwner(this)

        // Set up LiveData observation relationship
        viewModel.shoes.observe(viewLifecycleOwner, Observer { shoeList ->
            shoeList.forEach { shoe ->
                val shoeBinding: ShoeViewBinding = DataBindingUtil.inflate(inflater, R.layout.shoe_view, container, false)
                shoeBinding.shoe = shoe
                binding.shoeList.addView(shoeBinding.root)
            }
        })

        setHasOptionsMenu(true)

        // Floating action button listener
        binding.newShoeButton.setOnClickListener {
            viewModel.newShoe()
            findNavController().navigate(ListingFragmentDirections.actionListingToDetails())
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        logOut()
        return NavigationUI.onNavDestinationSelected(item!!, view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun logOut() {
        findNavController().navigate(ListingFragmentDirections.actionListingToLogin())
    }
}