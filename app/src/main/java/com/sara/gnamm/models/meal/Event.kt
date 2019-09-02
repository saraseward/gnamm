package com.sara.gnamm.models.meal

import com.sara.gnamm.models.user.User
import java.util.*


data class Event(internal val owner: User, var recipe: Recipe, var address: String, var time: Date) {

    //Constructor with "description" field
    constructor(owner: User, recipe: Recipe, address: String, time: Date,
                description: String) : this(owner, recipe, address, time) {
        description.ifEmpty {
            throw RuntimeException("Eccezione per fare della logica così sembra che ho usato bene i constructor")
        }
    }

    var participants: MutableList<User>? = mutableListOf()
    val createdAt: Date = Date() //"val" because creation date is always the same
    var updatedAt: Date = Date() //"var" because it can be changed if the user updates the event
}