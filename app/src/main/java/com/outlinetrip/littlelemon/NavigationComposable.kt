package com.outlinetrip.littlelemon

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationComposable(getStartingScreenRoute: String, userSharedPreferences: SharedPreferences) {
    val navController = rememberNavController()
    NavHost(navController = navController , startDestination = getStartingScreenRoute ){
        composable(OnBoarding.route){
            OnBoardingScreen(navController,userSharedPreferences)
        }
        composable(Home.route){
            HomeScreen(navController,userSharedPreferences)
        }
    }
}