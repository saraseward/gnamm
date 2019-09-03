package com.sara.gnamm.models.meal

import com.sara.gnamm.models.user.User
import java.util.*

data class Recipe(
        val id: Int = Random().nextInt(),
        val name: String,
        val description: String,
        var filters: MutableList<Filter>? = mutableListOf(),
        var ingredients: MutableList<String>? = mutableListOf(),
        val user: User
)