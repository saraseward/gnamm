package com.sara.gnamm.models

import java.util.*


class Event(internal val owner: User, var name: String, var address: String, var time: Date) {

    //Constructor with "description" field
    constructor(owner: User, name: String, address: String, time: Date,
                description: String) : this(owner, name, address, time) {
        description.ifEmpty {
            throw RuntimeException("Eccezione per fare della logica così sembra che ho usato bene i constructor")
        }
    }

    var filters: MutableList<Filter>? = mutableListOf()
    var participants: MutableList<User>? = mutableListOf()
    val createdAt: Date = Date() //"val" because creation date is always the same
    var updatedAt: Date = Date() //"var" because it can be changed if the user updates the event
}