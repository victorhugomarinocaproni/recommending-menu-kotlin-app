package com.pi.recommendingmenu.recipes.presentation.models

import com.pi.recommendingmenu.recipes.domain.Ingredient
import com.pi.recommendingmenu.recipes.domain.Recipe


data class RecipeUI(
    val name: String,
    val ingredients: List<String>,
)

fun Recipe.toRecipeUI(): RecipeUI {
    return RecipeUI(
        name = name,
        ingredients = ingredients
    )
}

//fun Calendar.toDisplayableTime(): DisplayableTime {
//    val hours = this.get(Calendar.HOUR_OF_DAY)
//    val minutes = this.get(Calendar.MINUTE)
//
//    val formattedTime = "$hours: hours and $minutes: minutes"
//
//    return DisplayableTime(
//        value = this,
//        formatted = formattedTime
//    )
//}


