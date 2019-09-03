package com.sara.gnamm.helper

import com.sara.gnamm.extensions.DefaultDateFormat
import com.sara.gnamm.models.meal.Recipe
import com.sara.gnamm.models.user.Credentials
import com.sara.gnamm.models.user.Sex
import com.sara.gnamm.models.user.User
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

//User
fun randomUser(): User {
    val random = Random().nextInt()
    return User(
            name = "Sara $random",
            lastName = "Seward $random",
            sex = Sex.Female,
            birthDate = randomBirthDate(),
            credentials = Credentials(username = "saraseward", password = "a"),
            address = "Via E. Stassano 29, 25125 Brescia (BS)"
    )
}

/*
fun listOfRandomUser(): MutableList<User> {
    val users = arrayListOf<User>()

    val random = Random().nextInt(20) + 1 //max 20 users
    for (i in 0..random) {
        users.add(randomUser())
    }

    return users
}
*/

fun randomBirthDate(): Date {
    val simpleDateFormat = SimpleDateFormat(DefaultDateFormat, Locale.getDefault())

    return try {
        val limit = 1970
        val randomYear = Random().nextInt(2019 - limit) + limit
        simpleDateFormat.parse("01/01/$randomYear") ?: Date()
    } catch (e: ParseException) {
        simpleDateFormat.parse("15/07/1997") ?: Date()
    }
}

//Recipe
fun randomRecipe(): Recipe {
    val random = Random().nextInt()
    return Recipe(
            name = "Pasta al pesto $random",
            description = "Pesto pasta $random",
            ingredients = mutableListOf("pasta", "pesto", "pepe"),
            user = randomUser()
    )
}

/*
fun listOfRandomRecipes(): MutableList<Recipe> {
    val recipes = arrayListOf<Recipe>()

    val random = Random().nextInt(20) + 1 //max 20 users
    for (i in 0..random) {
        recipes.add(randomRecipe())
    }

    return recipes
}
*/

fun <T> listOfRandomStuff(f: () -> T): MutableList<T> {
    val list = arrayListOf<T>()

    val random = Random().nextInt(20) + 1 //max 20 users
    for (i in 0..random) {
        list.add(f())
    }

    return list
}