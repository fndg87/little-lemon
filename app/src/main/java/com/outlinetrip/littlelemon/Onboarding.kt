package com.outlinetrip.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Onboarding(){

    var firstName by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var lastName by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var email by remember {
        mutableStateOf(TextFieldValue(""))
    }

    Column() {
        Row(horizontalArrangement = Arrangement.Center) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(0.5f)
            )
        }

        Text(text = "Let's get to know you")

        Text(text = "Personal information")

        Text(text = "First name")
        TextField(value = firstName, onValueChange = {firstName = it}, label = { Text(text = "Tilly")}, modifier = Modifier.padding(10.dp))

        Text(text = "Last name")
        TextField(value = lastName, onValueChange = {lastName = it}, label = { Text(text = "Doe")}, modifier = Modifier.padding(10.dp))

        Text(text = "Email")
        TextField(value = email, onValueChange = {email = it}, label = { Text(text = "example@hotmail.com")}, modifier = Modifier.padding(10.dp))

        Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(10.dp)) {
            Text(text = "Register")
        }
    }


}


@Preview(
    name = "Onboarding",
    showBackground = true
)
@Composable
fun previewOnboarding(){
    Onboarding()
}