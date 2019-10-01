package com.sara.gnamm.helper

import java.util.*

fun <T> listOfRandomStuff(f: () -> T): MutableList<T> {
    val list = arrayListOf<T>()

    val random = Random().nextInt(20) + 1 //max 20 users
    for (i in 0..random) {
        list.add(f())
    }

    return list
}