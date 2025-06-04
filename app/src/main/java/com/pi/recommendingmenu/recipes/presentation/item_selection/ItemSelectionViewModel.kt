package com.pi.recommendingmenu.recipes.presentation.item_selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pi.recommendingmenu.core.domain.util.Result
import com.pi.recommendingmenu.recipes.domain.RecipeDataSource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ItemSelectionViewModel(
    private val dataSource: RecipeDataSource
): ViewModel() {

    private val _state = MutableStateFlow(
        ItemSelectionState(
            isLoading = false,
        )
    )

    val state = _state.onStart {
        getRecommendedRecipes()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), _state.value)

    private val _events = Channel<ItemSelectionEvents>()
    val events = _events.receiveAsFlow()

    fun onAction(action: ItemSelectionActions) {
        when (action) {
            is ItemSelectionActions.LoadIngredients -> {
                getRecommendedRecipes()
            }
        }
    }


    private fun getRecommendedRecipes() {
        viewModelScope.launch {
            when(val res = dataSource.getRecipes()) {
                is Result.Success-> {
                    _state.update { it.copy(isLoading = false) }
                }
                is Result.Error -> {
                    _state.update { it.copy(isLoading = false) }
                    _events.send(
                        ItemSelectionEvents.Error("Failed to load recipes: ${res.error}")
                    )
                }
            }
        }
    }


}