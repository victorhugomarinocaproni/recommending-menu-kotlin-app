package com.pi.recommendingmenu.recipes.presentation.item_selection

sealed interface ItemSelectionActions {
    data object LoadIngredients : ItemSelectionActions
}