package com.knz21.sideeffect.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SwitchWithLabel(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.width(16.dp))
        Switch(
            modifier = Modifier.padding(vertical = 16.dp),
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
fun ButtonWithLabel(
    label: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Button(onClick = onClick) {
            Text(text = label)
        }
    }
}

@Composable
fun Counter(
    modifier: Modifier = Modifier,
    count: Int,
    onCountUp: () -> Unit,
    onCountDown: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = onCountDown) { Text(text = "-") }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.width(16.dp))
        Button(onClick = onCountUp) { Text(text = "+") }
    }
}

@Composable
fun Logs(logs: List<String>) {
    LazyColumn {
        itemsIndexed(
            items = logs
        ) { index, item ->
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = if (index % 2 == 0) Color.LightGray.copy(alpha = 0.4f) else Color.White
                    ),
                text = item
            )
        }
    }
}