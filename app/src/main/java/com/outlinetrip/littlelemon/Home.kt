package com.outlinetrip.littlelemon

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.outlinetrip.littlelemon.ui.theme.*
import com.outlinetrip.littlelemon.utils.FakeSharedPreferences


@Composable
fun HomeScreen(
    navController: NavHostController,
    userSharedPreferences: SharedPreferences,
    allMenuItems: List<MenuItemRoom>
) {
    //    sendUnauthenticatedUserOut(navController, userSharedPreferences)
    var menuItems = allMenuItems
    var menuCategorySelected by remember { mutableStateOf("All") }
    var searchPhrase by remember { mutableStateOf("") }
    val onCategoryButtonClick = { selectedCategory:String -> menuCategorySelected=selectedCategory}
    val onTextFieldValueChange = {searchPhraseValue:String-> searchPhrase = searchPhraseValue}

    Column {
        OnBoardingHeader()
        ShowHeroAndSearchBar(onTextFieldValueChange)
        Spacer(modifier = Modifier.padding(5.dp))
        ShowCategoryFilterButtons(onCategoryButtonClick)
        Divider(modifier = Modifier.padding(5.dp))

        if (searchPhrase.isNotEmpty()){
            menuItems = menuItems.filter { it.title.lowercase().contains(searchPhrase.lowercase()) }
        }
        MenuItemsList(items = filterByCategoryMenu(menuItems,menuCategorySelected))
    }
}

@Composable
fun ShowHeroAndSearchBar(onTextFieldValueChange: (String) -> Unit) {
    Surface(modifier = Modifier.fillMaxWidth()) {
        Column() {
            Text(text = stringResource(R.string.brand_name), style = MaterialTheme.typography.h2, color= llYellow)
            Text(text = stringResource(R.string.main_city), style = MaterialTheme.typography.h1, color = llWhite)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(5f)) {
                    Text(text = stringResource(id = R.string.ad_description_banner), style = MaterialTheme.typography.body2.copy(fontSize = 16.sp), color= llWhite)
                }
                Image(painter = painterResource(id = R.drawable.heroimage),
                    contentDescription = "Hero Image for banner",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(40.dp)),
                )
            }
            var searchPhraseValue by remember { mutableStateOf("") }
            Column {
                TextField(
                    value = searchPhraseValue,
                    onValueChange = {value ->
                        run {
                            searchPhraseValue = value;
                            onTextFieldValueChange(searchPhraseValue)
                        }
                    },
                    label = { Text(text = "Enter search phrase")},
                    leadingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = llWhite,
                        cursorColor = Color.Black,
                        disabledLabelColor = llYellow,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
            }
        }
    }
}

fun sendUnauthenticatedUserOut(navController: NavHostController, userSharedPreferences: SharedPreferences){
    if (!userSharedPreferences.getBoolean("onboarded",false)){
        navController.navigate(OnBoarding.route)
    }
}

@Composable
fun ShowCategoryFilterButtons(onCategoryButtonClick: (String) -> Unit) {
    val categories = listOf("All","Starters","Mains","Dessert","Drinks")
    Text(text = "ORDER FOR DELIVERY!", fontWeight = FontWeight.Bold, fontSize = 30.sp, modifier = Modifier.padding(top = 10.dp))
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
        categories.forEach {
            Button(onClick = { onCategoryButtonClick(it) },
                colors = ButtonDefaults.buttonColors(backgroundColor = llGrey),
                modifier = Modifier.padding(1.dp),
                shape = RoundedCornerShape(20.dp)) {
                    Text(text = it, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
        }
    }
}



fun filterByCategoryMenu(menuList: List<MenuItemRoom>, category:String): List<MenuItemRoom> {
    val menuItems: List<MenuItemRoom> = menuList.filter { it.category == category.lowercase() }
    return when(menuItems.size){
        0 -> menuList
        else -> menuItems
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MenuItemsList(items: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 20.dp)
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                Row(
                ) {
                    Column() {
                        Text(text = menuItem.title, fontSize = 28.sp,fontWeight = FontWeight.Bold)
                        Text(
                            menuItem.description,
                            fontSize = 20.sp, color = llGreyTwo,
                            modifier = Modifier.fillMaxWidth(0.6f)
                        )
                        Text(
                            "$%.2f".format(menuItem.price.toDouble()),
                            fontWeight = FontWeight.Bold,
                            color = llGreyTwo,
                            fontSize = 32.sp,
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                GlideImage(
                    model = menuItem.image,
                    contentDescription = menuItem.title,
                    alignment = Alignment.TopCenter,
                    modifier = Modifier.clip(RoundedCornerShape(10.dp)
                    )
                )
                }

                Divider()
            }
        )
    }

}
@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen(){
    LittlelemonTheme() {
        val allMenuItems = listOf(
            MenuItemRoom(
                1,
                "Testing Title1",
                "testing description 1 testing description 1 testing description 1 testing description 1 testing description 1 testing description 1",
                "10.0",
                "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/pasta.jpg",
                "Starters"
            ),
            MenuItemRoom(
                2,
                "Testing Title2",
                "testing description 1 testing description 1 testing description 1 testing description 1 testing description 1",
                "20.0",
                "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/pasta.jpg",
                "Starters"
            )
        )
        HomeScreen(rememberNavController(), FakeSharedPreferences(), allMenuItems)
    }
}