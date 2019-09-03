package com.sara.gnamm.repository

import com.sara.gnamm.helper.listOfRandomStuff
import com.sara.gnamm.helper.randomRecipe
import com.sara.gnamm.models.meal.Recipe


interface RecipeRepository {
    fun findAll(): MutableList<Recipe>
    fun findAllByUserId(id: Int): MutableList<Recipe>
    fun find(id: Int, userId: Int): Recipe
    fun save(recipe: Recipe, userId: Int): Recipe
    fun update(recipe: Recipe): Recipe
    fun delete(recipe: Recipe)
    fun deleteAllByUserId(userId: Int)
}

class RecipeRepositoryMock : RecipeRepository {

    var recipes = mutableListOf<Recipe>()

    override fun findAll(): MutableList<Recipe> {
        recipes = listOfRandomStuff { randomRecipe() }
        return recipes
    }

    override fun findAllByUserId(id: Int): MutableList<Recipe> {
        return recipes.filter { id == it.user.id }.toMutableList()
    }

    override fun find(id: Int, userId: Int): Recipe {
        return if (recipes.filter { id == it.id && userId == it.user.id }.size == 1) recipes[0] else throw RuntimeException("Recipe for user not found")
    }

    override fun save(recipe: Recipe, userId: Int): Recipe {
        recipes.add(recipe)
        return find(recipe.id, userId)
    }

    override fun update(recipe: Recipe): Recipe {
        var old = find(recipe.id, recipe.user.id)
        var idx = recipes.indexOf(old)
        recipes[idx] = recipe
        return recipes[idx]
    }

    override fun delete(recipe: Recipe) {
        recipes.removeIf { recipe.id == it.id && recipe.user.id == it.user.id }
    }

    override fun deleteAllByUserId(userId: Int) {
        recipes.removeIf { userId == it.user.id }
    }
}