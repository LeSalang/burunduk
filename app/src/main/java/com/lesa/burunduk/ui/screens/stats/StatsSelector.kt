package com.lesa.burunduk.ui.screens.stats

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.lesa.burunduk.ui.components.MyTextBold
import com.lesa.burunduk.ui.components.StatsDropDownMenu
import com.lesa.burunduk.ui.theme.BlackBlue

class StatsSelectorItem<T>(
    val value: T,
    val name: String
) {

}
@Composable
fun <T> StatsSelector(
    modifier: Modifier = Modifier,
    isExpanded: MutableState<Boolean>,
    selectedMenuItem: StatsSelectorItem<T>,
    onSelect: (StatsSelectorItem<T>) -> Unit,
    listOfMenuItems: List<StatsSelectorItem<T>>
) {
    Row(
        modifier = modifier
            .clickable {
                isExpanded.value = !isExpanded.value
            }
    ) {
        MyTextBold(
            text = selectedMenuItem.name,
            color = BlackBlue,
            modifier = Modifier
        )
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "",
            tint = BlackBlue,
            modifier = Modifier
        )
        StatsDropDownMenu(
            isExpanded = isExpanded,
            onDismissRequest = {
                isExpanded.value = !isExpanded.value
            },
            selectedMenuItem = selectedMenuItem,
            listOfMenuItems = listOfMenuItems,
            onSelect = onSelect
        )
    }
}

