package com.udacity.shoestore.screens.listing

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ListingFragmentBinding

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
            var linearLayout: LinearLayout = binding.shoeList.findViewById(R.id.shoe_list)
            shoeList.forEach { shoe ->
                val view: View = layoutInflater.inflate(R.layout.shoe_view, null)
                val shoeNameTextView: TextView = view.findViewById(R.id.shoe_name)
                val shoeSizeTextView: TextView = view.findViewById(R.id.size)
                val shoeCompanyTextView: TextView = view.findViewById(R.id.company_name)
                val shoeDescriptionTextView: TextView = view.findViewById(R.id.shoe_description)

                shoeNameTextView.text = shoe.name
                shoeSizeTextView.text = shoe.size.toString()
                shoeCompanyTextView.text = shoe.company
                shoeDescriptionTextView.text = shoe.description

                linearLayout.addView(view)
            }
        })

        setHasOptionsMenu(true)

        // Floating action button listener
        binding.newShoeButton.setOnClickListener {
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