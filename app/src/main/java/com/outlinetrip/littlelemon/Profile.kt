package com.outlinetrip.littlelemon

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.outlinetrip.littlelemon.ui.theme.LittlelemonTheme
import com.outlinetrip.littlelemon.utils.FakeSharedPreferences
import com.outlinetrip.littlelemon.utils.SharedPreferencesCommons.removeKeyInSharedPreferences

@Composable
fun ProfileScreen(navController: NavHostController, userSharedPreferences: SharedPreferences) {
    Column(modifier= Modifier.fillMaxWidth()) {
        OnBoardingHeader()
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