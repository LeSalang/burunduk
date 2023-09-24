package com.lesa.burunduk.ui.components

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.lesa.burunduk.ui.theme.BlackBlue
import com.lesa.burunduk.ui.theme.Red

@Composable
fun MyFloatingActionButton(
    onClick: () -> Unit,
    imageVector: ImageVector,
    containerColor: Color = BlackBlue,
    contentColor: Color = Red
) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = containerColor,
        contentColor = contentColor
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = ""
        )
    }
}
