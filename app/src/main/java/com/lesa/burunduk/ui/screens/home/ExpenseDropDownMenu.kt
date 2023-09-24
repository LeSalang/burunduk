package com.lesa.burunduk.ui.screens.home

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.lesa.burunduk.ui.theme.BlackBlue
import com.lesa.burunduk.ui.theme.Red

@Composable
fun ExpenseDropDownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest
    ) {
        DropdownMenuItem(
            text = {
                Text(text = "taracan")
            },
            onClick = { /*TODO*/ },
            colors = MenuItemColors(
                textColor = Red,
                leadingIconColor =BlackBlue,
                trailingIconColor = BlackBlue,
                disabledTextColor = BlackBlue,
                disabledLeadingIconColor = BlackBlue,
                disabledTrailingIconColor = BlackBlue
            )
        )
        DropdownMenuItem(
            text = {
                Row {
                    Icon(imageVector = Icons.Default.ThumbUp, contentDescription = "")
                    Text(text = "dggd")
                }
            },
            onClick = { /*TODO*/ },
            colors = MenuItemColors(
                textColor = Red,
                leadingIconColor =BlackBlue,
                trailingIconColor = BlackBlue,
                disabledTextColor = BlackBlue,
                disabledLeadingIconColor = BlackBlue,
                disabledTrailingIconColor = BlackBlue
            )
        )
    }
}