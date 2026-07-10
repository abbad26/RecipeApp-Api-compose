package com.techhub.recipeapp.data.repository

import com.techhub.recipeapp.data.remote.RecipeAPIService
import com.techhub.recipeapp.data.remote.dto.RecipeDTO
import com.techhub.recipeapp.domain.repository.RecipeRepository

class RecipeRepositoryImpl(private val apiService: RecipeAPIService): RecipeRepository {

    override suspend fun getAllRecipes(): List<RecipeDTO> {
        return apiService.getAllRecipes().recipes
    }

    override suspend fun getRecipeById(id: Int): RecipeDTO {
        return apiService.getRecipeByID(id)
    }
}