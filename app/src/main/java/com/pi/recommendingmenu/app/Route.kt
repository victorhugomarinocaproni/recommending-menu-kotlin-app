package com.pi.recommendingmenu.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object ItemSelection : Route

    @Serializable
    data object RecipeList : Route
}