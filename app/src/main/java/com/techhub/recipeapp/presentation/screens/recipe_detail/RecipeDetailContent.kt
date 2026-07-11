package com.techhub.recipeapp.presentation.screens.recipe_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Egg
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.techhub.recipeapp.data.remote.dto.RecipeDTO
import com.techhub.recipeapp.ui.theme.Orange

@Composable
fun RecipeDetailContent(
    details : RecipeDTO
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        AsyncImage(
            model = details.image,
            contentDescription = details.name,
            modifier = Modifier
                .fillMaxSize()
                .height(228.dp)
                .clip(RoundedCornerShape(24.dp)),
            contentScale = ContentScale.Crop
        )

        DetailSection(title = "Recipe Details", icon = Icons.Default.RemoveRedEye) {

            Text(
                text = details.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(12.dp))
            // Row (cuisine, difficulty, meal typ)

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                InfoChip(
                    label = details.cuisine,
                    icon = Icons.Default.Public
                )

                InfoChip(
                    label = details.difficulty,
                    icon = Icons.Default.Star
                )

                InfoChip(
                    label = details.mealType.firstOrNull() ?: "",
                    icon = Icons.Default.Bolt
                )
            }
        }

        DetailSection(title = "At a Glance", icon = Icons.Default.Timer) {

            StatsItem(
                icon = Icons.Default.Schedule,
                label = "Prep",
                value = "${details.prepTimeMinutes}m"
            )

            HorizontalDivider(
                modifier = Modifier.height(36.dp),
                thickness = 1.dp,
                color = Color.Gray.copy(alpha = 0.2f)
            )

            StatsItem(
                icon = Icons.Default.Whatshot,
                label = "Cooking",
                value = "${details.cookTimeMinutes}m"
            )

            HorizontalDivider(
                modifier = Modifier.height(36.dp),
                thickness = 1.dp,
                color = Color.Gray.copy(alpha = 0.2f)
            )

            StatsItem(
                icon = Icons.Default.Egg,
                label = "Serves",
                value = "${details.servings}"
            )

            HorizontalDivider(
                modifier = Modifier.height(36.dp),
                thickness = 1.dp,
                color = Color.Gray.copy(alpha = 0.2f)
            )

            StatsItem(
                icon = Icons.Default.LocalFireDepartment,
                label = "Calories",
                value = "${details.caloriesPerServing}"
            )
        }

        DetailSection(title = "Ingredients", icon = Icons.Default.Restaurant) {
            details.ingredients.forEach { ingredient ->
                Row(
                    modifier = Modifier.padding(vertical = 4.dp),
                    verticalAlignment = Alignment.Top
                ) {

                    // box for bullet point
                    Box(
                        modifier = Modifier
                            .padding(top = 7.dp)
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(color = Orange)
                    )
                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = ingredient,
                            color = Color.DarkGray.copy(alpha = 0.85f)
                        )

                }
            }
        }

        DetailSection(title = "Instruction", icon = Icons.Default.Info) {

            details.instructions.forEachIndexed { index, instruction ->

                Row(
                    modifier = Modifier.padding(vertical = 6.dp),
                    verticalAlignment = Alignment.Top
                ) {

                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(color = Orange),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = "${index + 1}",
                            fontSize = 12.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = instruction,
                        color = Color.DarkGray.copy(alpha = 0.85f),
                        modifier = Modifier.padding(top = 4.dp)
                    )


                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}