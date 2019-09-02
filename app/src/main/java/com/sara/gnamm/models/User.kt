package com.sara.gnamm.models

import java.util.*

enum class Sex {
    Male, Female, Other, RatherNotSay
}

class User(

        var id: Int,

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