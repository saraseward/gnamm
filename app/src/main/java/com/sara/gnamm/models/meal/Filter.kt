package com.sara.gnamm.models.meal


enum class Filter {
    GlutenFree, LactoseFree, Vegetarian, Vegan, NutsFree,
    //TODO "other" with var description: String? = null field.
    //TODO description can be "set" only to type Other
}
