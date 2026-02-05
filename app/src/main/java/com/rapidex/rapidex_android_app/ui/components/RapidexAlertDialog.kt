package com.rapidex.rapidex_android_app.ui.components

import android.widget.Button
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rapidex.rapidex_android_app.R

@Composable
fun RapidexAlertDialog(
    modifier: Modifier = Modifier,
    onCancel: ()->Unit,
    onConfirm: ()->Unit,
    title: String,
    text: String?,
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onCancel,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            if (text != null){
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        confirmButton = {
            PrimaryButton (
                text = stringResource(R.string.confirm_label),
                onClick = onConfirm
            )
        },
        dismissButton = {
            MutedButton(
                text = stringResource(R.string.cancel_label),
                onClick = onCancel
            )
        }
    )
}