package com.outlinetrip.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.outlinetrip.littlelemon.ui.theme.LittlelemonTheme
import com.outlinetrip.littlelemon.ui.theme.llWhite
import com.outlinetrip.littlelemon.ui.theme.llYellow
import android.content.SharedPreferences
import com.outlinetrip.littlelemon.utils.FakeSharedPreferences
import com.outlinetrip.littlelemon.utils.SharedPreferencesCommons.saveStringBooleanKPInSharedPreference
import com.outlinetrip.littlelemon.utils.SharedPreferencesCommons.saveStringToStringInSharedPreference

@Composable
fun OnBoardingScreen(navController: NavHostController, userSharedPreferences: SharedPreferences) {
    Column(modifier=Modifier.fillMaxWidth()) {
        OnBoardingHeader()
        OnBoardingForm(navController,userSharedPreferences)
    }
}

@Composable
fun OnBoardingHeader(){
    Row(modifier=Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .size(width = 300.dp, height = 100.dp)
        )
    }


        Surface(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Let's get to know you",
                style = MaterialTheme.typography.h1,
                color = llWhite,
                modifier = Modifier.padding(20.dp),
                textAlign = TextAlign.Center
            )
        }
}
@Composable
fun OnBoardingForm(navController: NavHostController, userSharedPreferences: SharedPreferences) {
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
        Spacer(modifier = Modifier.padding(5.dp))
        Text(
            text = "Personal information",
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Column(modifier = Modifier.padding(10.dp)) {

            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "First name", style = MaterialTheme.typography.caption)
            OutlinedTextField(
                value = firstName,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = llWhite,
                    cursorColor = Color.Black,
                    disabledLabelColor = llYellow,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = { firstName = it },
                label = { Text(text = "Tilly") },
                modifier = Modifier
                    .fillMaxWidth()
                )

            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "Last name", style = MaterialTheme.typography.caption)
            OutlinedTextField(
                value = lastName,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = llWhite,
                    cursorColor = Color.Black,
                    disabledLabelColor = llYellow,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                onValueChange = { lastName = it },
                label = { Text(text = "Doe") },
                modifier = Modifier
                    .fillMaxWidth()
            )


            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "Email", style = MaterialTheme.typography.caption)
            OutlinedTextField(
                value = email,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = llWhite,
                    cursorColor = Color.Black,
                    disabledLabelColor = llYellow,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                onValueChange = { email = it },
                label = { Text(text = "example@hotmail.com") },
                modifier = Modifier
                    .fillMaxWidth())


            Spacer(modifier = Modifier.padding(20.dp))
            Button(
                onClick = {
                    saveStringBooleanKPInSharedPreference("onboarded", true, userSharedPreferences)
                    saveStringToStringInSharedPreference("firstname", firstName.text, userSharedPreferences)
                    saveStringToStringInSharedPreference("lastname", lastName.text, userSharedPreferences)
                    saveStringToStringInSharedPreference("email", email.text, userSharedPreferences)
                    navController.navigate(Home.route)
                          },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "Register")
            }
        }
    }

}


@Preview(
    name = "OnBoarding",
    showBackground = true
)
@Composable
fun PreviewOnboarding() {
    LittlelemonTheme() {
        OnBoardingScreen(rememberNavController(), FakeSharedPreferences())
    }

}