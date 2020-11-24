package com.udacity.shoestore.screens.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ListViewModel : ViewModel() {

    // The current inventory of shoes
    private var _shoes = MutableLiveData<Collection<Shoe>>()
    val shoes: LiveData<Collection<Shoe>>
        get() = _shoes

    private val _newShoe = MutableLiveData<Shoe>()
    val newShoe: LiveData<Shoe>
        get() = _newShoe

    init {
        _shoes.value = shoeList()
    }

    /**
     * Initializes the list of shoes i.e. initial stock of shoes in inventory
     */
    private fun shoeList() : Collection<Shoe> {
        return mutableListOf(
            Shoe("Air Jordan", 8.5, "Nike",
            "The classic produced for basketball legend Michael Jordan.",
            mutableListOf("Nike Air Jordan")),
            Shoe("Samba", 8.0, "Adidas",
            "One of Adidas' most popular shoes. An ageless classic.",
            mutableListOf("Adidas Samba")),
            Shoe("Chuck Taylor All-Stars", 7.5, "Converse",
            "Converse's signature basketball sneakers for the filthy casual.",
            mutableListOf("Converse Chuck Taylor All-Stars")),
            Shoe("Hangisi", 7.0, "Manolo Blahnik",
            "Turkish inspiration. Romantic and artistic. A pair of quality heels.",
            mutableListOf("Manolo Blahnik Hangisi")),
            Shoe("Fetto", 8.0, "Jimmy Choo", "Star-studded and glamourous.",
            mutableListOf("Jimmy Choo Fetto")),
            Shoe("Pigalle", 8.0, "Christian Louboutin",
            "Elegant and timeless, an essential addition to every shoe lovers' collection",
            mutableListOf("Christian Louboutin Pigalle"))
        )
    }
}