package com.pi.recommendingmenu.recipes.presentation.item_selection

sealed interface ItemSelectionActions {
    data class OnToggleIngredient(
        val ingredient: String,
        val isSelected: Boolean
    ) : ItemSelectionActions
    data class OnToggleModel(
        val model: String,
        val isSelected: Boolean
    ) : ItemSelectionActions
    data object ValidateSelection : ItemSelectionActions
}