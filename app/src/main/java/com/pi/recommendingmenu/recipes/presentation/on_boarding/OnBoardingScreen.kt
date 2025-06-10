package com.pi.recommendingmenu.recipes.presentation.on_boarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pi.recommendingmenu.R
import com.pi.recommendingmenu.recipes.presentation.item_selection.ItemSelectionActions
import com.pi.recommendingmenu.recipes.presentation.item_selection.ItemSelectionState
import com.pi.recommendingmenu.ui.theme.robotoFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingScreenRoot(
    viewModel: OnBoardingViewModel,
    onNavigate: (String, String) -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold { innerPadding ->
        WelcomeScreen(
            state = state,
            onAction = viewModel::onAction,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White),
        )
    }
}

@Composable
fun WelcomeScreen(
    state: ItemSelectionState,
    modifier: Modifier = Modifier,
    onAction: (ItemSelectionActions) -> Unit,
) {

    val pagerState = rememberPagerState(pageCount = { 3 } )
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState,
            key = { it },
            pageSize = PageSize.Fill
        ) { index ->

            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    text = "Bem-Vindo ao TacoMatch",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp),
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 60.sp,
                    color = Color(0xFF605D06),
                    lineHeight = 60.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = buildAnnotatedString {
                        append("Trazemos uma nova experiência para a ")
                        pushStyle(SpanStyle(color = Color(0xFF8C8915)))
                        append("culinária mexicana")
                        pop()
                        append(", unindo o gostoso ao divertido!")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(16.dp),
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = buildAnnotatedString {
                        append("Primeira vez? Relaxa, é só seguir o ")
                        pushStyle(SpanStyle(color = Color(0xFF8C8915)))
                        append("On Boarding")
                        pop()
                        append(" que vai dar tudo certo!")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(16.dp),
                    fontFamily = robotoFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )

                Image(
                    painter = painterResource(id = R.drawable.taco_with_shadow),
                    contentDescription = "Welcome Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(16.dp),
                    alignment = Alignment.Center,
                )
            }
        }

        Box(
            modifier = Modifier
                .offset(y = (-50).dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 30.dp)
                .align(Alignment.BottomCenter)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(3) { index ->
                        Box(
                            modifier = Modifier
                                .height(6.dp)
                                .width(90.dp)
                                .clip(RoundedCornerShape(100))
                                .background(
                                    if (index <= pagerState.currentPage) Color.Green else Color.LightGray
                                )
                        )
                    }
                }

                Spacer(modifier = Modifier.padding(8.dp))

                when(pagerState.currentPage) {
                    0 -> {}
                    1 -> {}
                    2 -> {}
                }

                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(horizontal = 30.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF605D06)
                    ),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(
                        text = "PROSSEGUIR",
                        fontFamily = robotoFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.White,
                    )
                }
            }
        }
    }
}