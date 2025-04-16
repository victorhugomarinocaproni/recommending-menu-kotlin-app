package com.pi.recommendingmenu.recipes.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(
    val id: String,
    val name: String,
    val ingredients: List<IngredientDto>,
)