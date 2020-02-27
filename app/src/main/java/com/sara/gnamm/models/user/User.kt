package com.sara.gnamm.models.user

import com.sara.gnamm.extensions.DefaultDateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

enum class Sex {
    Male, Female, Other, RatherNotSay
}

//Only one primary constructor
data class User(

        val id: String,

        var name: String,

        var sex: Sex,

        var birthDate: Date,

        var credentials: Credentials,

        var address: String,

        var email: String,

        var socials: List<SocialInfo> = listOf()

        //TODO:
        //Filters (gluten free, vegan, vegetarian)
        //Review/stars
        //Visits
        //Preferred times
        //Recipes
        //Guests (happy, unhappy, were already friends, were not friends)
){

    companion object {
        @JvmStatic
        fun mock(): User {
            val random = Random().nextInt(30)
            return User(
                    id = "$random",
                    name = "Sara $random",
                    sex = Sex.Female,
                    email = "saraseward$random@gmail.com",
                    birthDate = birthDate(),
                    credentials = Credentials(username = "saraseward", password = "a"),
                    address = "Via E. Stassano 29, 25125 Brescia (BS)"
            )
        }


        private fun birthDate(): Date {
            val simpleDateFormat = SimpleDateFormat(DefaultDateFormat, Locale.getDefault())

            return try {
                val limit = 1970
                val randomYear = Random().nextInt(2019 - limit) + limit
                simpleDateFormat.parse("01/01/$randomYear") ?: Date()
            } catch (e: ParseException) {
                simpleDateFormat.parse("15/07/1997") ?: Date()
            }
        }
    }
}