package com.lesa.burunduk.ui.screens.shoppingList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lesa.burunduk.ui.components.MyTextBold
import com.lesa.burunduk.ui.viewmodels.ShoppingListViewModel


@Composable
fun ShoppingListTitle(
    name: String,
    viewModel: ShoppingListViewModel
) {
    Row(
       modifier = Modifier
           .fillMaxWidth(),
       horizontalArrangement = Arrangement.SpaceBetween
    ) {
        MyTextBold(text = name)
        MyTextBold(text = "10/13")
        Icon(imageVector = Icons.Default.Add, contentDescription = "")
        Icon(imageVector = Icons.Default.Delete, contentDescription = "")
        Icon(imageVector = Icons.Default.Share, contentDescription = "")
        Icon(imageVector = Icons.Default.Create, contentDescription = "")
    }
}