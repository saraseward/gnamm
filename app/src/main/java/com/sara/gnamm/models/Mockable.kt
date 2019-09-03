package com.sara.gnamm.models

interface Mockable {
    fun compare(that: Mockable): Boolean
}