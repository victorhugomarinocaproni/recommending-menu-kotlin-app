package com.pi.recommendingmenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.pi.recommendingmenu.recipes.presentation.RecipesViewModel
import com.pi.recommendingmenu.recipes.presentation.recipe_list.RecipesScreenRoot
import com.pi.recommendingmenu.ui.theme.RecommendingMenuTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecommendingMenuTheme {
                RecipesScreenRoot(
                    viewModel = RecipesViewModel(),
                    onBackClick = { /* TODO */ } ,
                )
            }
        }
    }
}


