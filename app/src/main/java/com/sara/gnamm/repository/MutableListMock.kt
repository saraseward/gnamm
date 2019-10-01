package com.sara.gnamm.repository

import com.sara.gnamm.models.Mockable

/**
This list implements its own version of indexOf as to make it easier to work with lists in mock repos
 */
class MutableListMock<T:Mockable> (val list: MutableList<T> = mutableListOf()) : MutableList<T>  by list {
    override fun indexOf(element: T): Int {
        for ((idx, value) in list.withIndex()) {
            if (value.mockCompare(element)) {
                return idx
            }
        }
        throw RuntimeException("No element found")
    }

    //add is ok
    //get/find can't be implemented generally
    override fun remove(element: T): Boolean {
        list.removeAt(list.indexOf(element))
        return true
    }

    fun update(element: T){
        val indexOf = list.indexOf(element)
        list[indexOf] = element
    }
}