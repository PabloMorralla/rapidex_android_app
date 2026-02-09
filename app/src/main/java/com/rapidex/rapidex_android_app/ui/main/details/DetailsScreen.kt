package com.rapidex.rapidex_android_app.ui.main.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rapidex.rapidex_android_app.R
import com.rapidex.rapidex_android_app.data.model.Order
import com.rapidex.rapidex_android_app.ui.components.ColumnCard
import com.rapidex.rapidex_android_app.ui.components.RapidexAlertDialog
import com.rapidex.rapidex_android_app.ui.main.viewmodel.OrderStatus

@Composable
fun DetailsScreen (
    modifier: Modifier = Modifier,
    order: Order?,
    getOrderStatus: (Order) -> OrderStatus
) {
    Column(
        modifier = modifier
            .padding(25.dp)
            .fillMaxSize(),
    ) {
        if (order == null){
            ColumnCard {
                Text(
                    text = stringResource(R.string.details_no_order_selected),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        else {
            ColumnCard {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.details_order_id),
                        style = MaterialTheme.typography.labelLarge
                    )
                    Spacer(modifier.weight(1f))
                    Text(
                        text = stringResource(R.string.order_id_format, order.id),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.details_employee_name),
                        style = MaterialTheme.typography.labelLarge
                    )
                    Spacer(modifier.weight(1f))
                    Text(
                        text = if (order.employee == null)
                            stringResource(R.string.details_order_unclaimed)
                        else
                            order.employee.firstName + " " + order.employee.lastName,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.details_order_status),
                        style = MaterialTheme.typography.labelLarge
                    )
                    Spacer(modifier.weight(1f))
                    Text(
                        text = when (getOrderStatus(order)) {
                            OrderStatus.UNCLAIMED -> stringResource(R.string.details_order_status_unclaimed)
                            OrderStatus.IN_PROCESS -> stringResource(R.string.details_order_status_in_process)
                            OrderStatus.FINISHED -> stringResource(R.string.details_order_status_finished)
                        },
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
