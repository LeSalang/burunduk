package com.lesa.burunduk.ui.components

import androidx.compose.foundation.background
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.lesa.burunduk.ui.screens.stats.StatsSelectorItem
import com.lesa.burunduk.ui.theme.LeSaTheme

@Composable
fun <T> StatsDropDownMenu(
    isExpanded: MutableState<Boolean>,
    onDismissRequest: () -> Unit,
    selectedMenuItem: StatsSelectorItem<T>,
    onSelect: (StatsSelectorItem<T>) -> Unit,
    listOfMenuItems: List<StatsSelectorItem<T>>
) {
    DropdownMenu(
        expanded = isExpanded.value,
        onDismissRequest = onDismissRequest,
        modifier = Modifier
//            .size(200.dp, 400.dp)
            .background(LeSaTheme.colors.background90)
    ) {
        listOfMenuItems.forEach {
            DropdownMenuItem(
                text = {
                    MyText(
                        text = it.name,
                        color = if (it == selectedMenuItem.value) LeSaTheme.colors.primary else LeSaTheme.colors.text
                    )
                },
                onClick = {
                    onSelect(it)
                    isExpanded.value = !isExpanded.value
                },
                modifier = Modifier
            )
        }
    }

}