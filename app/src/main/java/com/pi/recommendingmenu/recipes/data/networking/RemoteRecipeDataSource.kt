package com.pi.recommendingmenu.recipes.data.networking

import com.pi.recommendingmenu.core.data.networking.constructUrl
import com.pi.recommendingmenu.core.data.networking.safeCall
import com.pi.recommendingmenu.core.domain.util.NetworkError
import com.pi.recommendingmenu.core.domain.util.Result
import com.pi.recommendingmenu.core.domain.util.map
import com.pi.recommendingmenu.recipes.data.mappers.toRecipe
import com.pi.recommendingmenu.recipes.data.networking.dto.RecipeResponseDto
import com.pi.recommendingmenu.recipes.domain.Recipe
import com.pi.recommendingmenu.recipes.domain.RecipeDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class RemoteRecipeDataSource(
    private val httpClient: HttpClient
): RecipeDataSource {
    override suspend fun getRecipes(): Result<List<Recipe>, NetworkError> {
        return safeCall<RecipeResponseDto> {
            httpClient.post(
                urlString = constructUrl("knn/recommendation")
            ) {
                setBody("""
                    {
                        "ingredients": [
                            "cogumelo",
                            "pimenta jalapeno",
                            "coentro"
                        ]
                    }
                """.trimIndent())
            }
        }.map { response ->
            response.recommendations.map { it.toRecipe() }
        }
    }
}