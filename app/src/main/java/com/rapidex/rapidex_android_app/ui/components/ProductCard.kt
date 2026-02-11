package com.rapidex.rapidex_android_app.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rapidex.rapidex_android_app.data.model.Product

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: Product
) {
    val done = product.done

    RowCard (
        modifier = modifier,
        colors = CardColors(
            containerColor = if (done) MaterialTheme.colorScheme.tertiaryContainer
                else MaterialTheme.colorScheme.primaryContainer,
            contentColor = if (done) MaterialTheme.colorScheme.onTertiaryContainer
                else MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContainerColor = if (done) MaterialTheme.colorScheme.tertiaryContainer
                else MaterialTheme.colorScheme.primaryContainer,
            disabledContentColor = if (done) MaterialTheme.colorScheme.onTertiaryContainer
                else MaterialTheme.colorScheme.onPrimaryContainer,
        )
    ) {
        Icon(
            imageVector = if (done) Icons.Default.CheckCircle
                else Icons.Default.AddCircle,
            contentDescription = null
        )
        Spacer(Modifier.size(25.dp))
        Text(
            text = product.name,
            style = MaterialTheme.typography.bodyLarge,
            color = if (done) MaterialTheme.colorScheme.onTertiaryContainer
                else MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}