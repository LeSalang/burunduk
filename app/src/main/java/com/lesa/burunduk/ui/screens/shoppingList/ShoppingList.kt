package com.lesa.burunduk.ui.screens.shoppingList

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.lesa.burunduk.data.shoppingList.storage.DBShoppingListItem
import com.lesa.burunduk.ui.viewmodels.ShoppingListViewModel

@Composable
fun ShoppingList(
    shoppingList: List<DBShoppingListItem?>,
    /*listTitle: String,*/
    viewModel: ShoppingListViewModel
) {
    //viewModel.get(listTitle = listTitle)
    if (shoppingList.isNotEmpty()) {
        LazyColumn {
            items(shoppingList) {item ->
                ShoppingListItem(item = item!!, viewModel = viewModel)
            }
        }
    }

}