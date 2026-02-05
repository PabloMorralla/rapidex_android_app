package com.rapidex.rapidex_android_app.ui.auth.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.rapidex.rapidex_android_app.R
import com.rapidex.rapidex_android_app.data.model.Employee
import com.rapidex.rapidex_android_app.ui.components.ColumnCard
import com.rapidex.rapidex_android_app.ui.components.PasswordPrimaryTextField
import com.rapidex.rapidex_android_app.ui.components.PrimaryButton
import com.rapidex.rapidex_android_app.ui.components.PrimaryTextField
import com.rapidex.rapidex_android_app.ui.components.RapidexTopAppBar

@Composable
fun LoginScreen (
    modifier: Modifier = Modifier,
    onLogin: (String, String) -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ColumnCard (
            modifier = Modifier.padding(25.dp)
        ) {

            PrimaryTextField(
                modifier = Modifier.padding(bottom = 25.dp),
                value = username,
                onValueChange = { username = it },
                label = stringResource(R.string.login_user_label),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            PasswordPrimaryTextField(
                modifier = Modifier.padding(bottom = 25.dp),
                value = password,
                onValueChange = {password = it},
                label = stringResource(R.string.login_password_label),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Send
                )
            )

            PrimaryButton(
                text = stringResource(R.string.login_access_text),
                onClick = {
                    onLogin(username, password)
                }
            )
        }
    }
}
