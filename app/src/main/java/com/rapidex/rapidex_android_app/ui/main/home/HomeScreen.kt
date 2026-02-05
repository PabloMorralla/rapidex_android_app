package com.rapidex.rapidex_android_app.ui.main.home

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rapidex.rapidex_android_app.R
import com.rapidex.rapidex_android_app.data.model.Order
import com.rapidex.rapidex_android_app.ui.components.OrderCard
import com.rapidex.rapidex_android_app.ui.components.RapidexAlertDialog

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    pendingOrders: List<Order>,
    claimedOrders: List<Order>,
    selectedOrderId: Int?,
    onSelectOrder: (Int)->Unit,
    onClaimOrder: ()->Unit,
){
    var showClaimOrderDialog by remember { mutableStateOf(false) }

    if (showClaimOrderDialog && selectedOrderId != null) {
        RapidexAlertDialog(
            onCancel = { showClaimOrderDialog = false },
            onConfirm = {
                onClaimOrder()
                showClaimOrderDialog = false
            },
            title = "Do you want to claim Order " + stringResource(
                R.string.order_id_format,
                selectedOrderId
            ) + "?",
            text = null
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(25.dp)
    ) {
        Text(
            text = stringResource(R.string.home_claimed_orders_label),
            style = MaterialTheme.typography.titleLarge,
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items (claimedOrders) { order ->
                val isSelected = (order.id == selectedOrderId)

                OrderCard(
                    modifier = Modifier
                        .padding(bottom = 25.dp)
                        .clickable{onSelectOrder(order.id)}
                        .border(
                            width = if (isSelected) 3.dp else 0.dp,
                            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    order = order
                )
            }
        }

        Spacer(modifier = Modifier.padding(5.dp))

        Text(
            text = stringResource(R.string.home_pending_orders_label),
            style = MaterialTheme.typography.titleLarge,
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items (pendingOrders) { order ->
                val isSelected = (order.id == selectedOrderId)

                OrderCard(
                    modifier = Modifier
                        .padding(bottom = 25.dp)
                        .clickable{
                            onSelectOrder(order.id)
                            showClaimOrderDialog = true
                        }
                        .border(
                            width = if (isSelected) 3.dp else 0.dp,
                            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    order = order
                )
            }
        }
    }
}