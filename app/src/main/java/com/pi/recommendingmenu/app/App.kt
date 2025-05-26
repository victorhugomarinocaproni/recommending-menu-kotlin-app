package com.pi.recommendingmenu.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.pi.recommendingmenu.recipes.presentation.item_selection.ItemSelectionScreenRoot
import com.pi.recommendingmenu.recipes.presentation.item_selection.ItemSelectionViewModel
import com.pi.recommendingmenu.recipes.presentation.recipe_list.RecipesScreenRoot
import com.pi.recommendingmenu.recipes.presentation.recipe_list.RecipesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun App() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.ItemSelection,
    ) {
        composable<Route.ItemSelection> {
            val viewModel = koinViewModel<ItemSelectionViewModel>()

            ItemSelectionScreenRoot(
                viewModel = viewModel,
                onNavigate = { navController.navigate(Route.RecipeList) }
            )
        }

        composable<Route.RecipeList> {
            val viewModel = koinViewModel<RecipesViewModel>()

            RecipesScreenRoot(
                viewModel = viewModel,
                onBackClick = { navController.navigate(Route.ItemSelection) },
            )
        }
    }
}
