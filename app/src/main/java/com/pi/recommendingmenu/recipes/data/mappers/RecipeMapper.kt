package com.pi.recommendingmenu.recipes.data.mappers

import com.pi.recommendingmenu.recipes.data.networking.dto.RecipeDto
import com.pi.recommendingmenu.recipes.domain.Recipe

fun RecipeDto.toRecipe(): Recipe {
    return Recipe(
        name = name,
        ingredients = ingredients
    )
}
