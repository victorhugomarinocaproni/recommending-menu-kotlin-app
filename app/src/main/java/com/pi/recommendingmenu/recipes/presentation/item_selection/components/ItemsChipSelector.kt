package com.pi.recommendingmenu.recipes.presentation.item_selection.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ItemsChipSelector(
    title: String,
    modifier: Modifier = Modifier,
    allItems: List<String>,
    selectedItems: Set<String>,
    onItemSelected: (String) -> Unit,
    limit: Int? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 100.dp),
            modifier = modifier
                .fillMaxWidth()
                .heightIn(max = 300.dp)
                .padding(top = 8.dp)
                .border(width = 1.dp, Color(0xFFDAD8D8), shape = RoundedCornerShape(8.dp))
                .padding(8.dp),
        ) {
            items(allItems) { ingredient ->
                val isSelected = selectedItems.contains(ingredient)

                val isMaxSelected = when {
                    limit == null -> false
                    else -> selectedItems.size >= limit
                }

                FilterChip(
                    modifier = modifier
                        .padding(4.dp)
                        .height(48.dp),
                    selected = isSelected,
                    onClick = {
                        if (!isMaxSelected || isSelected) {
                            onItemSelected(ingredient)
                        }
                    },
                    enabled = isSelected || !isMaxSelected,
                    label = {
                        Text(
                            text = ingredient,
                            modifier = modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color(0xFF4CAF50),
                        selectedLabelColor = Color.White,
                        containerColor = if (!isMaxSelected) Color.Transparent else Color.LightGray,
                        labelColor = if (!isMaxSelected) Color.Black else Color.Gray,
                    )
                )
            }
        }
    }
}