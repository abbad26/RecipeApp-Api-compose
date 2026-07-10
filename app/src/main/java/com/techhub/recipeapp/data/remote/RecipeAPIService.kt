package com.techhub.recipeapp.data.remote

import com.techhub.recipeapp.data.remote.dto.AddRecipeRequest
import com.techhub.recipeapp.data.remote.dto.RecipeDTO
import com.techhub.recipeapp.data.remote.dto.RecipeResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class RecipeAPIService(
    private val client: HttpClient
) {

    // to get all recipe from server
    suspend fun getAllRecipes(): RecipeResponse {
        return client
            .get("${KtorClient.BASE_URL}recipes")
            .body()
    }

    // to fetch item through Id
    suspend fun getRecipeByID(id : Int): RecipeDTO{
        return client.get(urlString = "${KtorClient.BASE_URL}recipes/$id").body()

    }

    // to add recipes
    suspend fun addRecipe(request: AddRecipeRequest){
        client.post(urlString = "${KtorClient.BASE_URL}recipes/add"){
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }
}