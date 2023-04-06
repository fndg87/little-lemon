package com.outlinetrip.littlelemon

import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase


@Entity(tableName = "menu_items")
data class MenuItemRoom(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: String
)

@Dao
interface MenuItemDao {
    @Insert
    fun insertMenuItem(menuItem: MenuItemNetwork)
    @Query("SELECT * FROM menu_items")
    fun getAllMenuItems() : LiveData<List<MenuItem>>
    @Delete
    fun deleteMenuItem(menuItem: MenuItem)
}

@Database(entities = [MenuItemRoom::class], version = 1)
abstract class DatabaseConnection: RoomDatabase() {
    abstract fun menuDao():MenuItemDao
}
