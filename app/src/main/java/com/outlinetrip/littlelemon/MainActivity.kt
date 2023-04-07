package com.outlinetrip.littlelemon

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface

import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.outlinetrip.littlelemon.ui.theme.LittlelemonTheme
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val client = HttpClient(Android){
        install(ContentNegotiation){
            json(contentType = ContentType("text","plain"))
        }
    }

    private val db by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java,"menu.db").build()
    }
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
                    NavigationComposable(getStartingScreenRoute, userSharedPreferences, db)
                }
            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            if (db.menuItemDao().isEmpty()) {
                saveMenuToDatabase(fetchMenuNetwork())
            }
        }
    }
    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.menuItemToRoom() }
        db.menuItemDao().insertAllMenuItem(*menuItemsRoom.toTypedArray())
    }
    private suspend fun fetchMenuNetwork(): List<MenuItemNetwork> {
        val menuList: MenuNetworkData = client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json").body()
        return menuList.menu
    }
    private fun getStartDestinationScreenRoute(userSharedPreferences: SharedPreferences): String {
        val isOnBoarded = userSharedPreferences.getBoolean("onboarded", false)
        return when {
            isOnBoarded -> Home.route
            else -> OnBoarding.route
        }
    }
}