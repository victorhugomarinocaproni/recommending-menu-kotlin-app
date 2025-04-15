package com.pi.recommendingmenu.recipes.presentation

sealed interface RecipeAction {
    data object OnLikeRecipe : RecipeAction
    data object OnLoadNextRecipe : RecipeAction
    data object OnBackClick : RecipeAction
}