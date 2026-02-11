package com.rapidex.rapidex_android_app.ui.main.incident

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rapidex.rapidex_android_app.R
import com.rapidex.rapidex_android_app.data.model.IncidentType
import com.rapidex.rapidex_android_app.data.model.Order
import com.rapidex.rapidex_android_app.ui.components.ColumnCard
import com.rapidex.rapidex_android_app.ui.components.PrimaryButton
import com.rapidex.rapidex_android_app.ui.components.RowCard

@Composable
fun IncidentScreen (
    modifier: Modifier = Modifier,
    order: Order?,
    onReportIncident: (type: IncidentType?, description: String, orderId: Int)->Unit
) {
    var selectedTypeIndex by remember { mutableStateOf<Int?>(null) }
    var description by remember {mutableStateOf("")}

    val infiniteTransition = rememberInfiniteTransition()
    val buttonHorizontalPadding by
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 10f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = EaseInOut),
                repeatMode = RepeatMode.Reverse
            )
        )

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(25.dp),
        contentAlignment = Alignment.Center
    ) {
        if (order == null){
            ColumnCard {
                Text(
                    text = stringResource(R.string.incident_no_order_selected),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        else {
            ColumnCard {
                Text(
                    modifier = Modifier.padding(bottom = 25.dp),
                    text = stringResource(R.string.incident_type),
                    style = MaterialTheme.typography.titleLarge
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(bottom=25.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(25.dp)
                ) {
                    items(IncidentType.entries) { incidenType ->
                        val selected = incidenType.ordinal == selectedTypeIndex

                        RowCard (
                            modifier = Modifier
                                .border(
                                    width = 3.dp,
                                    color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable(
                                    onClick = {
                                        selectedTypeIndex = incidenType.ordinal
                                    }
                                )
                        ) {
                            Text(
                                text = incidenType.stringRepresentation,
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        }
                    }
                }

                Text(
                    modifier = Modifier.padding(bottom = 25.dp),
                    text = stringResource(R.string.incident_description),
                    style = MaterialTheme.typography.titleLarge
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    maxLines = Int.MAX_VALUE
                )

                PrimaryButton(
                    modifier = Modifier
                        .padding(horizontal = buttonHorizontalPadding.dp),
                    text = stringResource(R.string.report_incident),
                    onClick = {
                        onReportIncident(
                            IncidentType.entries.find { it.ordinal == selectedTypeIndex },
                            description,
                            order.id
                        )
                    },
                    colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError,
                        disabledContainerColor = MaterialTheme.colorScheme.error,
                        disabledContentColor = MaterialTheme.colorScheme.onError,
                    )
                )
            }
        }
    }
}
