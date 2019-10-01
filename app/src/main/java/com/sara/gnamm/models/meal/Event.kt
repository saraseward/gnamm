package com.sara.gnamm.models.meal

import com.sara.gnamm.models.Mockable
import com.sara.gnamm.models.user.User
import java.util.*


data class Event(val id: String,

                 internal val user: User,

                 var recipe: Recipe,

                 var title: String,

                 var address: String,

                 var time: Date) : Mockable {
    override fun mockCompare(that: Mockable): Boolean {
        if (that is Event) {
            return this.id == that.id &&
                    this.user.id == that.user.id
        }
        throw RuntimeException("Can't mockCompare this Event with object of different type")
    }

    //Constructor with "description" field
    constructor(id: String, user: User, recipe: Recipe, title: String, address: String, time: Date,
                description: String) : this(id, user, recipe, title, address, time) {
        description.ifEmpty {
            throw RuntimeException("Eccezione per fare della logica così sembra che ho usato bene i constructor")
        }
    }

    var participants: MutableList<User>? = mutableListOf()
    val createdAt: Date = Date() //"val" because creation date is always the same
    var updatedAt: Date = Date() //"var" because it can be changed if the user updates the event

    companion object {
        @JvmStatic
        fun mock(): Event {
            val random = Random().nextInt(20)
            return Event(
                    id = "$random",
                    user = User.mock(),
                    recipe = Recipe.mock(),
                    title = "PastaParty",
                    address = "Via E. Stassano 29 25125 Brescia BS Italy",
                    time = Date(),
                    description = "$random"
                )
        }
    }
}