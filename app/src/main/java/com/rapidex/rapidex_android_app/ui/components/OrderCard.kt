package com.rapidex.rapidex_android_app.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rapidex.rapidex_android_app.R
import com.rapidex.rapidex_android_app.data.model.Order

@Composable
fun OrderCard(
    modifier: Modifier = Modifier,
    order: Order
) {
    ColumnCard (
        modifier = modifier
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