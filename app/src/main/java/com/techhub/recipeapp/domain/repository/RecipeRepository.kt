package com.techhub.recipeapp.domain.repository

import com.techhub.recipeapp.data.remote.dto.RecipeDTO

interface RecipeRepository {

    suspend fun getAllRecipes(): List<RecipeDTO>

    suspend fun getRecipeById(id: Int): RecipeDTO

}