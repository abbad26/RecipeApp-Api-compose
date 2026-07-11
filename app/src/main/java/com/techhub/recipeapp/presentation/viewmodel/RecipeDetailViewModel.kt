package com.techhub.recipeapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techhub.recipeapp.data.remote.KtorClient
import com.techhub.recipeapp.data.remote.RecipeAPIService
import com.techhub.recipeapp.data.remote.dto.RecipeDTO
import com.techhub.recipeapp.data.repository.RecipeRepositoryImpl
import com.techhub.recipeapp.domain.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeDetailViewModel: ViewModel() {
    private val repository : RecipeRepository =
        RecipeRepositoryImpl(apiService = RecipeAPIService(KtorClient.client))

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var recipe by mutableStateOf<RecipeDTO?>(null)
        private set


    fun getRecipeDetail(id : Int){

        isLoading = true
        errorMessage = null

        try {
            viewModelScope.launch {
                recipe = repository.getRecipeById(id)
            }
        }catch (e : Exception){
            errorMessage = e.message ?: "An unexpected error occurred"
        } finally {
            isLoading = false
        }
    }
}