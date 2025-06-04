package com.pi.recommendingmenu.recipes.domain

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val ingredients: List<String>,
    val name: String,
)
