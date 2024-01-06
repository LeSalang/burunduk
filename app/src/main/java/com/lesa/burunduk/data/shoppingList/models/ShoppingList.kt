package com.lesa.burunduk.data.shoppingList.models

import androidx.room.Embedded
import androidx.room.Relation
import com.lesa.burunduk.data.shoppingList.storage.DBShoppingList
import com.lesa.burunduk.data.shoppingList.storage.DBShoppingListItem
import java.util.UUID

data class ShoppingList(
    /*val id: UUID,
    val name: String,
    val lastModifiedTime: LocalDateTime,
    val isFavourite: Boolean = false,*/
    @Embedded
    val list: DBShoppingList,
    @Relation(
        parentColumn = "id",
        entityColumn = "shopping_list_id"
    )
    val items: List<DBShoppingListItem?>
)

data class ShoppingListItem(
    val id: UUID,
    val name: String,
    val isChecked: Boolean = false,
    val shoppingListID: UUID
)