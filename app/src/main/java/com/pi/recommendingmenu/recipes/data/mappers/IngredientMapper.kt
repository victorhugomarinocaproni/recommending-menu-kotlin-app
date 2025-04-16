package com.pi.recommendingmenu.recipes.data.mappers

import com.pi.recommendingmenu.recipes.data.networking.dto.IngredientDto
import com.pi.recommendingmenu.recipes.domain.Ingredient

fun IngredientDto.toIngredient(): Ingredient {
    return Ingredient(
        name = name
    )
}