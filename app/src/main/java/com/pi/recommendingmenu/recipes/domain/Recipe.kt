package com.pi.recommendingmenu.recipes.domain

data class Recipe(
    val id: String,
    val name: String,
    val ingredients: List<Ingredient>
)
