package com.lesa.burunduk.ui.screens.home.expenseTableView

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lesa.burunduk.R
import com.lesa.burunduk.ui.components.MyText
import com.lesa.burunduk.ui.navigation.AddExpense
import com.lesa.burunduk.ui.theme.LeSaTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpandedExpenseCard(
    expanded: Boolean,
    onExpandStateChanged: (Boolean) -> Unit,
    onDeleteClick: () -> Unit,
    date: String,
    category: String,
    local: String,
    rub: String,
    navController: NavController
) {
    Card(
        colors = CardColors(
            contentColor = LeSaTheme.colors.text,
            containerColor = LeSaTheme.colors.background90,
            disabledContainerColor = LeSaTheme.colors.background90,
            disabledContentColor = LeSaTheme.colors.background90
            ),
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
            onLongClickLabel = "LongClick",
            onLongClick = {
                onExpandStateChanged(!expanded)
            },
            onClick = {}
        )
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Column(
                Modifier.padding(10.dp)
            ) {
                Row {
                    MyText(text = "$date: $category")
                }
                Row {
                    Column {
                        MyText(text = "local = $local")
                        MyText(text = "rub = $rub")
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
                    onDeleteClick()
                },
                modifier = Modifier.padding(0.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    "delete"
                )
            }
            IconButton(onClick = {
                navController.navigate(AddExpense.route)
            }) {
                Icon(imageVector = Icons.Default.Settings, "")
            }
            IconButton(
                onClick = {
                    onExpandStateChanged(!expanded)
                }
            ) {
                Icon(painter = painterResource(id = R.drawable.remove), "")
            }
        }
    }
}
