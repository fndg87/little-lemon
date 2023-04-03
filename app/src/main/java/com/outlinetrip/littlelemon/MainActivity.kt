package com.outlinetrip.littlelemon

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.outlinetrip.littlelemon.ui.theme.LittlelemonTheme

class MainActivity : ComponentActivity() {
    private val userSharedPreferences: SharedPreferences by lazy { getSharedPreferences("LittleLemonUser", MODE_PRIVATE) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittlelemonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val getStartingScreenRoute: String = getStartDestinationScreenRoute(userSharedPreferences)
                    NavigationComposable(getStartingScreenRoute, userSharedPreferences)
                }
            }
        }
    }
    private fun getStartDestinationScreenRoute(userSharedPreferences: SharedPreferences): String {
        val isOnBoarded = userSharedPreferences.getBoolean("onboarded", false)
        return when {
            isOnBoarded -> Home.route
            else -> OnBoarding.route
        }
    }
}