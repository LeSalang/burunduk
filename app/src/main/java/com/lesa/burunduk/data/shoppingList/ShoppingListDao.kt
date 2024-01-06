package com.lesa.burunduk.data.shoppingList

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lesa.burunduk.data.shoppingList.models.ShoppingList
import com.lesa.burunduk.data.shoppingList.storage.DBShoppingList

@Dao
interface ShoppingListDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertList(list: DBShoppingList)

    @Query("SELECT * FROM shopping_list_table")
    suspend fun getAllShoppingLists(): List<ShoppingList>

    @Query("SELECT list_name FROM shopping_list_table")
    suspend fun getListOfShoppingListNames(): List<String>

    @Query("SELECT * FROM shopping_list_table WHERE list_name LIKE :listName")
    suspend fun getShoppingListByID(listName: String): ShoppingList

/*
    @Delete
    suspend fun deleteItem(item: ShoppingListItem)

    @Query ("SELECT list_title FROM shopping_list_table")
    suspend fun getListOfListTitles(): List<String>

    @Query ("SELECT * FROM shopping_list_table WHERE list_title LIKE :listTitle")
    suspend fun getShoppingList(listTitle: String): List<ShoppingListItem>*/
}