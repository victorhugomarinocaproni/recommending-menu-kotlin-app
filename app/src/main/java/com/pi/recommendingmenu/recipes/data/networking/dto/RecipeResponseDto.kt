package com.pi.recommendingmenu.recipes.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class RecipeResponseDto(
    val data: List<RecipeDto>
)