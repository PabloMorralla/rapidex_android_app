package com.rapidex.rapidex_android_app.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rapidex.rapidex_android_app.data.model.Product

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: Product
) {
    val done = product.done

    val containerColor = animateColorAsState(
        targetValue =  if (done) MaterialTheme.colorScheme.tertiaryContainer
            else MaterialTheme.colorScheme.primaryContainer,
        animationSpec = tween(
            durationMillis = 250,
            easing = EaseInOut
        )
    )
    val contentColor = animateColorAsState(
        targetValue = if (done) MaterialTheme.colorScheme.onTertiaryContainer
            else MaterialTheme.colorScheme.onPrimaryContainer,
        animationSpec = tween(
            durationMillis = 250,
            easing = EaseInOut
        )
    )

    RowCard (
        modifier = modifier,
        colors = CardColors(
            containerColor = containerColor.value,
            contentColor = contentColor.value,
            disabledContainerColor = containerColor.value,
            disabledContentColor = contentColor.value
        )
    ) {
        Icon(
            imageVector = if (done) Icons.Default.CheckCircle
                else Icons.Default.AddCircle,
            contentDescription = null
        )
        Spacer(Modifier.size(25.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = product.name,
            style = MaterialTheme.typography.bodyLarge,
            color = if (done) MaterialTheme.colorScheme.onTertiaryContainer
                else MaterialTheme.colorScheme.onPrimaryContainer
        )
        Spacer(Modifier.size(25.dp))
        AsyncImage(
            modifier = Modifier.height(100.dp),
            model = product.imageUrl,
            contentDescription = "Product Image",
            contentScale = ContentScale.Fit
        )
    }
}