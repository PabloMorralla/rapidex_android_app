package com.rapidex.rapidex_android_app.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.rapidex.rapidex_android_app.R

@Composable
fun PasswordPrimaryTextField (
    label: String,
    value: String,
    onValueChange: (String)->Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
) {
    var passwordVisible by remember { mutableStateOf(false) }

    TextField(
        modifier = modifier
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge
            )
        },
        visualTransformation =
            if (passwordVisible) VisualTransformation.None
            else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(
                onClick = {passwordVisible = !passwordVisible}
            ) {
                Icon(
                    painter = painterResource(
                        if (passwordVisible) R.drawable.ic_visibility_on
                        else R.drawable.ic_visibility_off
                    ),
                    contentDescription = null
                )
            }
        },
        keyboardOptions = keyboardOptions
    )
}