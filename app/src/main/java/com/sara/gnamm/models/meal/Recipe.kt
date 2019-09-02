package com.sara.gnamm.models.meal

data class Recipe(
        val name: String,
        val description: String,
        var filters: MutableList<Filter>? = mutableListOf(),
        var ingredients: MutableList<String>? = mutableListOf()
)