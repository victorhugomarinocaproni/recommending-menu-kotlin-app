package com.pi.recommendingmenu.recipes.presentation.item_selection

import androidx.compose.runtime.Immutable

@Immutable
data class ItemSelectionState(
    val isLoading: Boolean = false,
    val ingredients: List<String> = emptyList<String>(),
    val selectedIngredients: Set<String> = emptySet(),
    val models: List<String> = emptyList<String>(),
    val selectedModels: Set<String> = emptySet(),
    val isButtonEnabled: Boolean = false
)
