package com.outlinetrip.littlelemon

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.outlinetrip.littlelemon.ui.theme.LittlelemonTheme
import com.outlinetrip.littlelemon.utils.FakeSharedPreferences

@Composable
fun ProfileScreen(navController: NavHostController, userSharedPreferences: SharedPreferences) {

    sendUnauthenticatedUserOut(navController,userSharedPreferences)
    Column(modifier= Modifier.fillMaxWidth()) {
        LittleLemonHeader(navController)
        OnBoardingForm(navController = navController, userSharedPreferences = userSharedPreferences )
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen(){
    LittlelemonTheme() {
        ProfileScreen(rememberNavController(),FakeSharedPreferences())
    }

}