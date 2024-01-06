package com.lesa.burunduk.data.shoppingList.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

@Entity(tableName = "shopping_list_table")
data class DBShoppingList(
    @PrimaryKey
    @ColumnInfo (name = "list_id")
    val id: UUID,
    @ColumnInfo(name = "list_name")
    val name: String,
    @ColumnInfo(name = "last_modified_time")
    val lastModifiedTime: LocalDateTime,
    @ColumnInfo(name = "is_favourite")
    val isFavourite: Boolean
    )

@Entity (
    tableName = "shopping_list_item_table",
  /*  foreignKeys = @ForeignKey(
        entity = DBShoppingList::class,
        parentColumns = "list_title",
        childColumns = "list_title"
    )*/
    )
data class DBShoppingListItem(
    @PrimaryKey
    @ColumnInfo (name = "item_id")
    val id: UUID,
    @ColumnInfo(name = "item_name")
    val name: String,
    @ColumnInfo (name = "is_checked")
    val isChecked: Boolean,
    @ColumnInfo (name = "shopping_list_id")
    val shoppingListID: UUID

)


