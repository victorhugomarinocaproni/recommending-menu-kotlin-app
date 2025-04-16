package com.pi.recommendingmenu.recipes.domain

import com.pi.recommendingmenu.core.domain.util.NetworkError
import com.pi.recommendingmenu.core.domain.util.Result

interface RecipeDataSource {
    suspend fun getRecipes(): Result<List<Recipe>, NetworkError>
}