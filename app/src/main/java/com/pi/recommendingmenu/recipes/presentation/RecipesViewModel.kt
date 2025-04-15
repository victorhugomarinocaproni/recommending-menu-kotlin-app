package com.pi.recommendingmenu.recipes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pi.recommendingmenu.recipes.presentation.recipe_list.RecipeState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RecipesViewModel() : ViewModel() {
    private val _state = MutableStateFlow(
        RecipeState(
            isLoading = true,
            isLiked = false
        )
    )
    val state = _state.onStart {
        // TODO: getRecipes no Endpoint
        getRecommendedRecipes()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), _state.value)

    private val _events = Channel<RecipeEvents>()
    val events = _events.receiveAsFlow()

    fun onAction(action: RecipeAction) {
        when (action) {
            is RecipeAction.OnLikeRecipe -> {
                _state.value = state.value.copy(
                    isLiked = !state.value.isLiked
                )
            }

            is RecipeAction.OnBackClick -> {

            }

            else -> Unit
        }
    }

    private fun getRecommendedRecipes() {
        viewModelScope.launch {
            // Simulate network delay
            delay(1000L)
            _state.value = state.value.copy(
                isLoading = false
            )
        }
    }
}