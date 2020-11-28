package com.udacity.shoestore.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.DetailsFragmentBinding
import com.udacity.shoestore.screens.listing.ListViewModel

class DetailsFragment : Fragment() {

    val shoes: ListViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: DetailsFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.details_fragment, container, false)

        binding.shoes = shoes

        binding.cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }

        shoes.returnToList.observe(viewLifecycleOwner, Observer {
            if (it)
                findNavController().navigateUp()
        })

        return binding.root
    }
}
