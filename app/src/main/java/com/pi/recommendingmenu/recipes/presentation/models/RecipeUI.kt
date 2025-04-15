package com.pi.recommendingmenu.recipes.presentation.models

import com.pi.recommendingmenu.recipes.domain.Recipe


data class RecipeUI(
    val id: String,
    val name: String,
)

//data class DisplayableTime(
//    val value: Calendar,
//    val formatted: String
//)

fun Recipe.toRecipeUI(): RecipeUI {
    return RecipeUI(
        id = id,
        name = name,
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


