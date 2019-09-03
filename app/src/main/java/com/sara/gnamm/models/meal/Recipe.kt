package com.sara.gnamm.models.meal

import com.sara.gnamm.models.user.User
import java.util.*

data class Recipe(
        val id: Int,
        var name: String,
        var description: String,
        var filters: MutableList<Filter>? = mutableListOf(),
        var ingredients: MutableList<String>? = mutableListOf(),
        val user: User
) {
    companion object {
        @JvmStatic
        fun mock(): Recipe {
            val random = Random().nextInt(20)
            return Recipe(
                    id = random,
                    name = "Pasta",
                    description = "$random",
                    ingredients = mutableListOf("foo", "bar"),
                    user = User.mock()
            )
        }

    }
}