package com.pi.recommendingmenu.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
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
                onNavigate = { ingredients, model ->
                    navController.navigate(
                        Route.RecipeList(ingredients, model)
                    )
                }
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

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
) : T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}
