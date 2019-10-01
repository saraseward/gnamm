package com.sara.gnamm.models

/**
 * This interface is implemented by all objects and is used in mock repositories, tests, ...
 * to simulate a real comparison
 */
interface Mockable {
    fun mockCompare(that: Mockable): Boolean
}