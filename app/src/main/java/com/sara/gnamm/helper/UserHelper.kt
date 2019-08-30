package com.sara.gnamm.helper

import com.sara.gnamm.models.Credentials
import com.sara.gnamm.models.Sex
import com.sara.gnamm.models.User
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun isOver18(user: User): Boolean {
    return yearsBetween(user.birthDate) >= 18
}

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

fun listOfRandomUser(): List<User> {
    val users = arrayListOf<User>()

    val random = Random().nextInt(20) + 1 //max 20 users
    for (i in 0..random) {
        users.add(randomUser())
    }

    return users
}


fun randomBirthDate(): Date {
    val randomYear = try {
        val limit = 1970
        Random().nextInt(2019 - limit) + limit
    } catch (e: ParseException) {
        1900
    }

    val simpleDateFormat = SimpleDateFormat(DefaultDateFormat, Locale.getDefault())
    return simpleDateFormat.parse("01-01-$randomYear") ?: Date()

}

