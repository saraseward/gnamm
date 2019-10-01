package com.sara.gnamm.repository

import com.sara.gnamm.models.meal.Filter

interface FilterRepository {
    fun findAll(): List<Filter> = listOf(Filter.GlutenFree, Filter.LactoseFree, Filter.NutsFree, Filter.Vegan, Filter.Vegetarian)
    fun findByName(name: String) = findAll().filter { name == it.name }
}

class FilterRepositoryMock : FilterRepository //Only default implementations