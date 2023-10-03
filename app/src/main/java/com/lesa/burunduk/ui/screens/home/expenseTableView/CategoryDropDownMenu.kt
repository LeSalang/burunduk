package com.lesa.burunduk.ui.screens.home.expenseTableView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lesa.burunduk.R
import com.lesa.burunduk.data.expenses.models.Category
import com.lesa.burunduk.data.expenses.models.nameId
import com.lesa.burunduk.ui.components.MyText
import com.lesa.burunduk.ui.theme.WhiteBlue

@Composable
fun CategoryDropDownMenu(
    isExpanded: MutableState<Boolean>,
    onDismissRequest: () -> Unit,
    selectedCategory: MutableState<Int>
) {
    val categoryList: List<Int> = listOf(R.string.category_all) + Category.values().map { it ->
        it.nameId
    }.toMutableList()
    DropdownMenu(
        expanded = isExpanded.value,
        onDismissRequest = onDismissRequest,
        modifier = Modifier
            .size(200.dp, 400.dp)
            .background(WhiteBlue)
    ) {
        categoryList.forEach {
            DropdownMenuItem(
                text = {
                    MyText(
                        text = stringResource(id = it)
                    )
                },
                onClick = {
                    selectedCategory.value = it
                    isExpanded.value = !isExpanded.value
                          },
                modifier = Modifier
            )
        }
    }
}