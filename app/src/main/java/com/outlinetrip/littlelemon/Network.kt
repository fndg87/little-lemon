package com.outlinetrip.littlelemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
* Fetching data from
* https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json
* */
@Serializable
class MenuNetworkData(
    @SerialName("menu")
    val menu: List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: String,
    @SerialName("image")
    val image: String,
    @SerialName("category")
    val category: String
) {
    fun menuItemToRoom() = MenuItemRoom(id, title, description,price, image,category)
}