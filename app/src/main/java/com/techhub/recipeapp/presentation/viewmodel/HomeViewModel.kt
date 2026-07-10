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

class HomeViewModel: ViewModel() {
    private val repository : RecipeRepository =
        RecipeRepositoryImpl(apiService = RecipeAPIService(KtorClient.client))

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var recipes by mutableStateOf<List<RecipeDTO>>(emptyList())
        private set

    var categories by mutableStateOf<List<String>>(listOf("All"))
        private set

    var selectedCategory by mutableStateOf("All")
        private set

    private var allRecipes : List<RecipeDTO> = emptyList()

    init {
        getRecipes()
    }
    fun getRecipes(){

        isLoading = true
        errorMessage = null


        viewModelScope.launch {
            try {

                val result = repository.getAllRecipes()
                allRecipes = result
                recipes = result

                val cuisines = result.map { it.cuisine }.distinct().sorted()
                categories = listOf("All") + cuisines

                applyFilters()
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: "An unexpected error occurred"
            } finally {
                isLoading = false
            }
        }
    }

    fun onCategorySelected(category: String) {
        selectedCategory = category
        applyFilters()
    }

    private fun applyFilters(){
        recipes =
            if (selectedCategory == "All") {
            allRecipes
        } else {
            allRecipes.filter { it.cuisine == selectedCategory }
        }
    }
}