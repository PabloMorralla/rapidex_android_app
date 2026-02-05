package com.rapidex.rapidex_android_app.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rapidex.rapidex_android_app.R
import com.rapidex.rapidex_android_app.data.model.Order

@Composable
fun OrderCard(
    modifier: Modifier = Modifier,
    order: Order,
    isSelected: Boolean
) {
    ColumnCard (
        modifier = modifier
            .border(
                width = if (isSelected) 3.dp else 0.dp,
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Text(
            text = stringResource(R.string.order_id_format, order.id),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = order.products.joinToString { it.name },
            style = MaterialTheme.typography.bodyMedium
        )
    }
}