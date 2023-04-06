package com.outlinetrip.littlelemon

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.outlinetrip.littlelemon.ui.theme.LittlelemonTheme
import com.outlinetrip.littlelemon.ui.theme.llGrey
import com.outlinetrip.littlelemon.ui.theme.llWhite
import com.outlinetrip.littlelemon.ui.theme.llYellow
import com.outlinetrip.littlelemon.utils.FakeSharedPreferences


@Composable
fun HomeScreen(navController: NavHostController, userSharedPreferences: SharedPreferences) {
    val appContext = LocalContext.current
    val db by lazy {
        Room.databaseBuilder(appContext,DatabaseConnection::class.java,"menu.db")
    }
//    sendUnauthenticatedUserOut(navController, userSharedPreferences)
    Column {
        OnBoardingHeader()
        ShowWhoAreWeAndSearchBar()
        Spacer(modifier = Modifier.padding(5.dp))
        ShowCategoryFilterButtons()
        Divider(modifier = Modifier.padding(5.dp))
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
                    modifier = Modifier.size(120.dp)
                )
            }
            Column() {
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text(text = "Enter search phrase")},
                    leadingIcon = {
                            IconButton(
                                onClick = {}
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Clear"
                                )
                            }
                        },
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

@Composable
fun DisplayMenuItemsAll(){

}
@Preview(showBackground = true, )
@Composable
fun PreviewHomeScreen(){
    LittlelemonTheme() {
        HomeScreen(rememberNavController(), FakeSharedPreferences())

    }
}