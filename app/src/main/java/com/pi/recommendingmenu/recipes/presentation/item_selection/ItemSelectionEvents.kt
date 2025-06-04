package com.pi.recommendingmenu.recipes.presentation.item_selection


sealed interface ItemSelectionEvents {
    data class Error(val message: String) : ItemSelectionEvents
}