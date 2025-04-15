package com.pi.recommendingmenu.recipes.presentation.recipe_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pi.recommendingmenu.R
import com.pi.recommendingmenu.core.presentation.ObserveAsEvent
import com.pi.recommendingmenu.recipes.presentation.RecipeAction
import com.pi.recommendingmenu.recipes.presentation.RecipeEvents
import com.pi.recommendingmenu.recipes.presentation.RecipesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipesScreenRoot(
    viewModel: RecipesViewModel,
    onBackClick: () -> Unit,
) {

    val state = viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvent(events = viewModel.events) { event ->
        when (event) {
            is RecipeEvents.OnSuccess -> {
                // TODO: Implement like recipe action
            }

            is RecipeEvents.OnError -> {
                // TODO: Implement dislike recipe action
            }
        }
    }

    Scaffold(topBar = {
        TopAppBar(
            title = { "teste" },
            navigationIcon = {
                IconButton(onClick = { onBackClick() } ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                        contentDescription = "Back"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
        )
    }) { innerPadding ->
        RecipesScreen(
            state = state.value,
            onAction = viewModel::onAction,
            modifier = Modifier.padding(innerPadding).background(Color.White)
        )
    }

}


@Composable
fun RecipesScreen(
    state: RecipeState,
    modifier: Modifier = Modifier,
    onAction: (RecipeAction) -> Unit,
) {

    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(R.string.recipe_screen_title),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 30.dp)
                    .padding(top = 10.dp),
                fontSize = 30.sp,
                color = Color(0xFF605D06)
            )

            Text(
                text = "Tacos Chicos",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                fontSize = 46.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF605D06)
            )

            val texto = "Feito com os melhores ingredientes, " +
                    "esse prato trás a essência da culinária mexicana, " +
                    "feito para quem adora boa comida mas não consegue comer muito\n"

            Text(
                text = texto,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(top = 5.dp),
                fontSize = 16.sp
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = { onAction(RecipeAction.OnLikeRecipe) },
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .padding(horizontal = 5.dp),
                ) {
                    if (state.isLiked) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Like",
                            tint = Color.Red
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Like",
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(horizontal = 30.dp)
                    .padding(top = 5.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(Color.DarkGray),
            ) {
                /* TODO : TERÁ A IMAGEM DA RECIPE */
            }

            Button(
                onClick = { onAction(RecipeAction.OnLoadNextRecipe) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(top = 30.dp)
                    .height(60.dp),
                shape = RoundedCornerShape(10.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF605D06),
                    contentColor = Color.White,
                ),
            ) {
                Text(
                    text = "PRÓXIMA",
                    fontSize = 22.sp,
                )
            }
        }

    }
}
