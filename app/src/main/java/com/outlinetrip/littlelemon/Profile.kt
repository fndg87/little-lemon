package com.outlinetrip.littlelemon

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.outlinetrip.littlelemon.utils.SharedPreferencesCommons.removeKeyInSharedPreferences

@Composable
fun ProfileScreen(navController: NavHostController, userSharedPreferences: SharedPreferences) {
    Column() {
        Text(text = "Profile Screen")
        Button(onClick = {
            removeKeyInSharedPreferences("onboarded", userSharedPreferences)
            removeKeyInSharedPreferences("firstname", userSharedPreferences)
            removeKeyInSharedPreferences("lastname", userSharedPreferences)
            removeKeyInSharedPreferences("email", userSharedPreferences)
            navController.navigate(OnBoarding.route)
        }) {
            Text(text = "Profile")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun previewProfileScreen(){

}