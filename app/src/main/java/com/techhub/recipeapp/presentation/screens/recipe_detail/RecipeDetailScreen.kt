package com.techhub.recipeapp.presentation.screens.recipe_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.techhub.recipeapp.presentation.components.ErrorMessage
import com.techhub.recipeapp.presentation.components.LoadingIndicator
import com.techhub.recipeapp.presentation.components.MyTopBar
import com.techhub.recipeapp.presentation.viewmodel.RecipeDetailViewModel
import com.techhub.recipeapp.ui.theme.Orange

@Composable
fun RecipeDetailScreen(
    recipeId: Int,
    onBack: () -> Unit,
    viewModel: RecipeDetailViewModel = viewModel()
){

    LaunchedEffect(recipeId) {
        viewModel.getRecipeDetail(recipeId)
    }

    Scaffold(
        topBar = {
            MyTopBar(
                title = "Recipe Details",
                onBackClick = onBack,
                icon = Icons.AutoMirrored.Filled.ArrowBack
            )
        }
    ) { innerPadding ->

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(color = Orange.copy(alpha = 0.02f))
        ) {

            when{
                viewModel.isLoading -> LoadingIndicator(strokeWidth = 1.dp)

                viewModel.errorMessage != null ->
                    ErrorMessage(
                        errorMessage = viewModel.errorMessage,
                        onRetry = {viewModel.getRecipeDetail(recipeId)}
                    )

                viewModel.recipe != null -> {
                    RecipeDetailContent(details = viewModel.recipe!!)
                }
            }
        }

    }
}