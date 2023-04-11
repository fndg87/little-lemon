package com.outlinetrip.littlelemon

import android.content.SharedPreferences
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationComposable(
    getStartingScreenRoute: String,
    userSharedPreferences: SharedPreferences,
    db: AppDatabase
) {
    val navController = rememberNavController()
    NavHost(navController = navController , startDestination = getStartingScreenRoute ){
        composable(OnBoarding.route) {
            OnBoardingScreen(navController,userSharedPreferences)
        }
        composable(Home.route) {
            val allMenuItems by db.menuItemDao().getAllMenuItems().observeAsState(emptyList())
            HomeScreen(navController,userSharedPreferences, allMenuItems)
        }
        composable(Profile.route) {
            ProfileScreen(navController,userSharedPreferences)
        }
    }
}