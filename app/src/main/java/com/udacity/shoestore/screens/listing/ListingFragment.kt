package com.udacity.shoestore.screens.listing

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ListingFragmentBinding
import com.udacity.shoestore.databinding.ShoeViewBinding
import kotlinx.android.synthetic.main.shoe_view.view.*

class ListingFragment : Fragment() {

    private val viewModel by activityViewModels<ListViewModel>()

    private lateinit var binding: ListingFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.listing_fragment,
            container,
            false
        )

        // Set up LiveData observation relationship
        viewModel.shoes.observe(viewLifecycleOwner, Observer { shoeList ->
            shoeList.forEach { shoe ->
                val view: View = inflater.inflate(R.layout.shoe_view, null, false)
                view.shoe_name.text = shoe.name
                view.size.text = shoe.size.toString()
                view.company_name.text = shoe.company
                view.shoe_description.text = shoe.description

                binding.shoeList.addView(view)
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