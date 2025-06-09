package com.pi.recommendingmenu.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object ItemSelection : Route

    @Serializable
    data class RecipeList(
        val ingredients: String,
        val model: String
    ) : Route
}