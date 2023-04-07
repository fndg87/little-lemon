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

import com.outlinetrip.littlelemon.ui.theme.LittlelemonTheme
import com.outlinetrip.littlelemon.ui.theme.llGrey
import com.outlinetrip.littlelemon.ui.theme.llWhite
import com.outlinetrip.littlelemon.ui.theme.llYellow
import com.outlinetrip.littlelemon.utils.FakeSharedPreferences


@Composable
fun HomeScreen(
    navController: NavHostController,
    userSharedPreferences: SharedPreferences,
    allMenuItems: List<MenuItemRoom>
) {
    //    sendUnauthenticatedUserOut(navController, userSharedPreferences)

    Column {
        OnBoardingHeader()
        ShowWhoAreWeAndSearchBar()
        Spacer(modifier = Modifier.padding(5.dp))
        ShowCategoryFilterButtons()
        Divider(modifier = Modifier.padding(5.dp))
        MenuItemsList(items = allMenuItems)
    }

}

@Composable
fun ShowWhoAreWeAndSearchBar(){
    Surface(modifier = Modifier.fillMaxWidth()) {
        Column() {
            Text(text = stringResource(R.string.brand_name), style = MaterialTheme.typography.h2, color= llYellow)
            Text(text = stringResource(R.string.main_city), style = MaterialTheme.typography.h1, color = llWhite)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(5f)) {
                    Text(text = stringResource(id = R.string.ad_description_banner), style = MaterialTheme.typography.body2, color= llWhite)
                }
                Image(painter = painterResource(id = R.drawable.heroimage),
                    contentDescription = "Hero Image for banner",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(40.dp)),
                )
            }
            Column {
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text(text = "Enter search phrase")},
                    leadingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
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
fun ShowCategoryFilterButtons(){
    val categories = listOf("Starters","Mains","Dessert","Drinks")
    Text(text = "ORDER FOR DELIVERY!", fontWeight = FontWeight.Bold, fontSize = 30.sp)
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        categories.forEach {
            Button(onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = llGrey),
                modifier = Modifier.padding(6.dp),
                shape = RoundedCornerShape(20.dp)) {
                    Text(text = it, fontWeight = FontWeight.Bold)
            }
        }
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
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column {
                        Text(text=menuItem.title, fontWeight = FontWeight.Bold)
                        Text(menuItem.description,
                            fontSize = 18.sp,color = llGrey,
                            modifier = Modifier.fillMaxWidth(0.6f)
                        )
                        Text("$%.2f".format(menuItem.price.toDouble()), fontWeight = FontWeight.Bold, color = llGrey,fontSize = 28.sp)
                    }

                }
                GlideImage(
                    model = menuItem.image,
                    contentDescription = menuItem.title,
                    alignment = Alignment.TopCenter,
                    modifier = Modifier.size(100.dp).clip(RoundedCornerShape(10.dp)
                    )
                )
                Divider()
            }
        )
    }

}
@Preview(showBackground = true, )
@Composable
fun PreviewHomeScreen(){
    LittlelemonTheme() {
        val allMenuItems = listOf(
            MenuItemRoom(1,"Testing Title1", "testing description 1", "10.0", "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/pasta.jpg","Starters"),
            MenuItemRoom(2,"Testing Title2", "testing description 1", "20.0", "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/pasta.jpg","Starters")
        )
        HomeScreen(rememberNavController(), FakeSharedPreferences(), allMenuItems)

    }
}