package com.knz21.sideeffect.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun ProduceStateScreen() {
    val count by produceState(initialValue = 0) {
        while (true) {
            delay(1000L)
            value++
        }
    }
    Text(
        modifier = Modifier.padding(16.dp),
        text = "count: $count"
    )
}