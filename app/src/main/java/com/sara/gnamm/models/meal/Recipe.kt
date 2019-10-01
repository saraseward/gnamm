package com.sara.gnamm.models.meal

import com.sara.gnamm.models.Mockable
import com.sara.gnamm.models.user.User
import java.util.*

data class Recipe(
        val id: String,

        var name: String,

        var description: String,

        var filters: MutableList<Filter>? = mutableListOf(),

        var ingredients: MutableList<String>? = mutableListOf(),

        val user: User
) : Mockable {
    override fun mockCompare(that: Mockable): Boolean {
        if (that is Recipe) {
            return this.id == that.id &&
                    this.user.id == that.user.id
        }
        throw RuntimeException("Can't mock-compare this Recipe with object of different type")
    }

    companion object {
        @JvmStatic
        fun mock(): Recipe {
            val random = Random().nextInt(20)
            return Recipe(
                    id = "$random",
                    name = "Pasta",
                    description = "$random",
                    ingredients = mutableListOf("foo", "bar"),
                    user = User.mock()
            )
        }
    }
}