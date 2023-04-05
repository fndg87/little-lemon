package com.outlinetrip.littlelemon

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController, userSharedPreferences: SharedPreferences) {
    sendUnauthenticatedUserOut(navController, userSharedPreferences)
    Column() {
        Text(text = "Home Screen")
        Button(onClick = { navController.navigate(Profile.route) }) {
            Text(text = "Profile")
        }
    }

}

fun sendUnauthenticatedUserOut(navController: NavHostController, userSharedPreferences: SharedPreferences){
    if (!userSharedPreferences.getBoolean("onboarded",false)){
        navController.navigate(OnBoarding.route)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen(){

}