package com.lesa.burunduk.ui.screens.shoppingList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lesa.burunduk.ui.components.MyText
import com.lesa.burunduk.ui.theme.LeSaTheme
import com.lesa.burunduk.ui.viewmodels.ShoppingListViewModel

@Preview
@Composable
fun ShoppingListScreen(
    viewModel: ShoppingListViewModel = hiltViewModel(),
) {
    viewModel.getListOfAllShoppingList()
    Column(
        Modifier
            .padding(10.dp)
            .background(LeSaTheme.colors.background80)
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = LeSaTheme.colors.background80),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn {
                items(
                    viewModel.listOfAllShoppingLists.value
                ) {shoppingList ->
                    ShoppingListTitle(
                        name = shoppingList.list.name,
                        viewModel = viewModel
                    )
                    ShoppingList(
                        shoppingList = shoppingList.items,
                       /* listTitle = listTitle,*/
                        viewModel = viewModel
                    )
                }
                
            }
            Button(onClick = {

            }) {
                MyText(text = "add new shopping list")
            }
        }
    }
}