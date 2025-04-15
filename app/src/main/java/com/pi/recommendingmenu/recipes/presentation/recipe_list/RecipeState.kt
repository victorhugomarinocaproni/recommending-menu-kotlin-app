package com.pi.recommendingmenu.recipes.presentation.recipe_list

import androidx.compose.runtime.Immutable

@Immutable
data class RecipeState(
    val isLoading: Boolean = false,
    val isLiked: Boolean = false
)
