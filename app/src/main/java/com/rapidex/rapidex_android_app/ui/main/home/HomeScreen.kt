package com.rapidex.rapidex_android_app.ui.main.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rapidex.rapidex_android_app.R
import com.rapidex.rapidex_android_app.data.model.Order
import com.rapidex.rapidex_android_app.ui.components.OrderCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    pendingOrders: List<Order>,
    claimedOrders: List<Order>
){
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
            items (claimedOrders) {
                OrderCard(
                    modifier = Modifier.padding(bottom = 25.dp),
                    order = it
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
            items (pendingOrders) {
                OrderCard(
                    modifier = Modifier.padding(bottom = 25.dp),
                    order = it
                )
            }
        }
    }
}