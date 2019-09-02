package com.sara.gnamm.models

import java.util.*

enum class Sex {
    Male, Female, Other, RatherNotSay
}

//Only one primary constructor
data class User(

        val id: Int = Random().nextInt(),

        var name: String,

        var lastName: String,

        var sex: Sex,

        var birthDate: Date,

        var credentials: Credentials,

        var address: String

        //TODO:
        //Filters (gluten free, vegan, vegetarian)
        //Review/stars
        //Visits
        //Preferred times
        //Recipes
        //Guests (happy, unhappy, were already friends, were not friends)
)