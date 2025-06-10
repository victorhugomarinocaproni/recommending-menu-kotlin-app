package com.pi.recommendingmenu.recipes.presentation.item_selection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pi.recommendingmenu.R
import com.pi.recommendingmenu.recipes.presentation.item_selection.components.ItemsChipSelector
import com.pi.recommendingmenu.recipes.presentation.on_boarding.OnBoardingViewModel
import com.pi.recommendingmenu.recipes.presentation.utils.TypeConverter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemSelectionScreenRoot(
    viewModel: OnBoardingViewModel,
    onNavigate: (String, String) -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(stringResource(R.string.item_selection_screen_title)) },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
        )
    }) { innerPadding ->
        ItemSelectionScreen(
            state = state,
            onAction = viewModel::onAction,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White),
            onNavigate = onNavigate
        )
    }
}

@Composable
fun ItemSelectionScreen(
    state: ItemSelectionState,
    modifier: Modifier = Modifier,
    onAction: (ItemSelectionActions) -> Unit,
    onNavigate: (String, String) -> Unit
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ItemsChipSelector(
            title = "Selecione os ingredientes: ",
            modifier = Modifier,
            allItems = state.ingredients,
            selectedItems = state.selectedIngredients,
            onItemSelected = { ingredient ->
                if (state.selectedIngredients.contains(ingredient)) {
                    onAction(ItemSelectionActions.OnToggleIngredient(ingredient, true))
                } else {
                    onAction(ItemSelectionActions.OnToggleIngredient(ingredient, false))
                }
            },
            limit = 5
        )

        Spacer(modifier = Modifier.padding(top = 8.dp))

        ItemsChipSelector(
            title = "Selecione o modelo: ",
            modifier = Modifier,
            allItems = state.models,
            selectedItems = state.selectedModels,
            onItemSelected = { model ->
                if (state.selectedModels.contains(model)) {
                    onAction(ItemSelectionActions.OnToggleModel(model, true))
                    onAction(ItemSelectionActions.ValidateSelection)
                } else {
                    onAction(ItemSelectionActions.OnToggleModel(model, false))
                    onAction(ItemSelectionActions.ValidateSelection)
                }
            },
            limit = 1
        )

        Button(
            onClick = { onNavigate(
                TypeConverter.fromListToString(state.selectedIngredients.toList()),
                TypeConverter.fromListToString(state.selectedModels.toList())
            ) },
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(16.dp),
            enabled = state.isButtonEnabled
        ) {
            Text(text = "RECEBER RECOMENDAÇÃO")
        }
    }
}