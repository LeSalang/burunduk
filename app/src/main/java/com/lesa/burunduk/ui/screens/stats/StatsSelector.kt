package com.lesa.burunduk.ui.screens.stats

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.lesa.burunduk.ui.components.MyDropDownMenu
import com.lesa.burunduk.ui.components.MyTextBold
import com.lesa.burunduk.ui.theme.BlackBlue

@Composable
fun StatsSelector(
    modifier: Modifier = Modifier,
    isExpanded: MutableState<Boolean>,
    selectedMenuItem: MutableState<String>,
    listOfMenuItems: List<String>
) {
    Row(
        modifier = modifier
    ) {
        MyTextBold(
            text = selectedMenuItem.value,
            color = BlackBlue,
            modifier = Modifier
                // .weight(2.5f)
                .clickable {
                    isExpanded.value = !isExpanded.value
                }
        )
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "",
            tint = BlackBlue,
            modifier = Modifier
                .clickable {
                    isExpanded.value = !isExpanded.value
                }
        )
        MyDropDownMenu(
            isExpanded = isExpanded,
            onDismissRequest = {
                isExpanded.value = !isExpanded.value
            },
            selectedMenuItem = selectedMenuItem,
            listOfMenuItems = listOfMenuItems
            )
    }
}

