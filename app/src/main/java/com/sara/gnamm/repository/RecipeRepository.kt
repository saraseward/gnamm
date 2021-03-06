package com.sara.gnamm.repository

import com.sara.gnamm.models.meal.Recipe

interface RecipeRepository {
    fun findAll(): MutableList<Recipe>
    fun findAllByUserId(id: String): MutableList<Recipe>
    fun find(id: String, userId: String): Recipe
    fun save(recipe: Recipe): Recipe
    fun update(recipe: Recipe): Recipe
    fun delete(recipe: Recipe)
    fun deleteAllByUserId(userId: String)
}

class RecipeRepositoryMock(private val recipes: MutableListMock<Recipe> = MutableListMock()) : RecipeRepository{

    override fun findAll(): MutableList<Recipe> {
        return recipes.list
    }

    override fun findAllByUserId(id: String): MutableList<Recipe> {
        return recipes.filter { id == it.user.id }.toMutableList()
    }

    override fun find(id: String, userId: String): Recipe {
        return if (recipes.filter { id == it.id
                        && userId == it.user.id }.size == 1)
            recipes[0]
                else throw RuntimeException("Recipe for user not found")
    }

    override fun save(recipe: Recipe): Recipe {
        recipes.add(recipe)
        return find(recipe.id, recipe.user.id)
    }

    override fun update(recipe: Recipe): Recipe {
        recipes.update(recipe)
        return recipes[recipes.indexOf(recipe)]
    }

    override fun delete(recipe: Recipe) {
        recipes.remove(recipe)
    }

    override fun deleteAllByUserId(userId: String) {
        recipes.removeIf { userId == it.user.id }
    }
}