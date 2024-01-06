package com.lesa.burunduk.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lesa.burunduk.data.shoppingList.ShoppingListRoomDB
import com.lesa.burunduk.data.shoppingList.models.ShoppingList
import com.lesa.burunduk.data.shoppingList.storage.DBShoppingList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val shoppingListRoomDB: ShoppingListRoomDB
): ViewModel() {
    val shoppingListList = mutableStateOf(emptyList<ShoppingList>())
    val listOfShoppingListNames = mutableStateOf(emptyList<String>())
    val listOfAllShoppingLists = mutableStateOf(emptyList<ShoppingList>())
    lateinit var currentShoppingList: MutableState<ShoppingList>

   /* val listOfListTitles = mutableStateOf(emptyList<String>())
    val shoppingList = mutableStateOf(emptyList<ShoppingListItem>())
    val openShoppingListTitle = mutableStateOf(String)
*/

    fun insertShoppingList(list: DBShoppingList) = viewModelScope.launch {
        shoppingListRoomDB.dao.insertList(list = list)
    }

    fun getListOfAllShoppingList() = viewModelScope.launch {
        listOfAllShoppingLists.value = shoppingListRoomDB.dao.getAllShoppingLists()
    }

    fun getListOfShoppingListNames() = viewModelScope.launch {
        listOfShoppingListNames.value = shoppingListRoomDB.dao.getListOfShoppingListNames()
    }

    fun getShoppingListByName(listName: String) = viewModelScope.launch {
        currentShoppingList.value = shoppingListRoomDB.dao.getShoppingListByID(listName = listName)
    }

/*
    fun getListOfListTitles() = viewModelScope.launch {
        listOfListTitles.value = shoppingListRoomDB.dao.getListOfListTitles()
    }

    fun getShoppingList(listTitle: String) = viewModelScope.launch {
        shoppingList.value = shoppingListRoomDB.dao.getShoppingList(listTitle = listTitle)
    }

   */
/* suspend fun getShoppingList(listTitle: String): List<ShoppingListItem> {
        return shoppingListRoomDB.dao.getShoppingList(listTitle = listTitle)
    }*//*


    fun insertItem(item: ShoppingListItem) = viewModelScope.launch {
        shoppingListRoomDB.dao.insertItem(item = item)
    }

    fun deleteItem(item: ShoppingListItem) = viewModelScope.launch {
        shoppingListRoomDB.dao.deleteItem(item = item)
    }

    fun isShoppingListOpen(openShoppingList: List<ShoppingListItem>, currentShoppingList: List<ShoppingListItem>): Boolean {
        return (openShoppingList == currentShoppingList)
    }

*/

}