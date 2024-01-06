package com.lesa.burunduk.ui.screens.shoppingList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lesa.burunduk.data.shoppingList.storage.DBShoppingListItem
import com.lesa.burunduk.ui.components.MyText
import com.lesa.burunduk.ui.viewmodels.ShoppingListViewModel

@Composable
fun ShoppingListItem(
    item: DBShoppingListItem,
    viewModel: ShoppingListViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        MyText(text = item.name)
        Icon(imageVector = Icons.Default.Check, contentDescription = "")
        Icon(imageVector = Icons.Default.Delete, contentDescription = "")
        Icon(imageVector = Icons.Default.Create, contentDescription = "")
    }
}