package com.pi.recommendingmenu.recipes.presentation

interface RecipeEvents {
    data class OnSuccess(val message: String) : RecipeEvents
    data class OnError(val message: String) : RecipeEvents
}