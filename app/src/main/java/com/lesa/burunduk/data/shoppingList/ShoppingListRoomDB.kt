package com.lesa.burunduk.data.shoppingList

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lesa.burunduk.data.shoppingList.storage.DBShoppingList
import com.lesa.burunduk.data.shoppingList.storage.DBShoppingListItem

@Database (
    entities = [
        DBShoppingListItem::class,
        DBShoppingList::class
    ],
    version = 1
)
abstract class ShoppingListRoomDB: RoomDatabase() {
    abstract val dao: ShoppingListDao
}