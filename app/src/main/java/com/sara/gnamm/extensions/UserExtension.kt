@file:JvmName("UserHelper")

package com.sara.gnamm.extensions

import com.sara.gnamm.models.user.User

fun User.isOver18(): Boolean {
    return birthDate.yearsBetween() >= 18
}

fun User.validate() {
    fun validateField(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException(
                    "Can't save user $id: empty $fieldName")
        }
    }
    validateField(name, "Name") //todo get from string resources
    validateField(address, "Address")
}

fun User.displayName(): String {
    return "$name $lastName"
}