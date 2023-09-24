package com.lesa.burunduk.ui.screens.home

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
import com.lesa.burunduk.R
import com.lesa.burunduk.ui.components.MyText
import com.lesa.burunduk.ui.theme.BlackBlue
import com.lesa.burunduk.ui.theme.Red
import com.lesa.burunduk.ui.theme.WhiteRed

@Composable
fun ExpandedExpenseCard(
    expanded: Boolean,
    onExpandStateChanged: (Boolean) -> Unit,
    onDeleteClick: () -> Unit,
    date: String,
    category: String,
    local: String,
    rub: String
) {
    Card(
        colors = CardColors(
            contentColor = BlackBlue,
            containerColor = WhiteRed,
            disabledContainerColor = WhiteRed,
            disabledContentColor = WhiteRed
            ),
        //shape = RectangleShape,
        //border = BorderStroke(1.dp, color = WhiteBlue),
        modifier = Modifier
            .fillMaxWidth()
            //.padding(horizontal = 10.dp),
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
            //horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                Modifier.padding(10.dp)
            ) {
                Row {
                    MyText(text = "$date: $category", color = Red)
                    /*Spacer(modifier = Modifier.size(5.dp))
                    MyText(text = "-")
                    Spacer(modifier = Modifier.size(5.dp))
                    MyText(text = category, color = Red)
*/
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
            IconButton(onClick = { /*TODO*/ }) {
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
