package com.udacity.shoestore.screens.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.InstructionsFragmentBinding
import com.udacity.shoestore.screens.welcome.WelcomeFragmentDirections

class InstructionsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: InstructionsFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.instructions_fragment, container, false)

        binding.instructionsButton.setOnClickListener {
            findNavController().navigate(InstructionsFragmentDirections.actionInstructionsToListing())
        }
        return binding.root
    }
}
