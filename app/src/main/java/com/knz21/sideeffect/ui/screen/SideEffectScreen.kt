package com.knz21.sideeffect.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.knz21.sideeffect.ui.component.Counter
import com.knz21.sideeffect.ui.component.Logs

@Composable
fun SideEffectScreen() {
    var count by remember { mutableIntStateOf(0) }
    val logs = remember { mutableStateListOf<String>() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "count")
        Counter(
            count = count,
            onCountUp = { count++ },
            onCountDown = { count-- }
        )
        SideEffectComposable(
            count,
            sendLog = { logs.add(0, it) }
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = "logs")
        Logs(logs = logs)
    }
}

@Composable
private fun SideEffectComposable(
    count: Int,
    sendLog: (log: String) -> Unit
) {
    var innerCount by remember { mutableIntStateOf(0) }
    fun createText(count: Int, innerCount: Int) = "count: $count innerCount: $innerCount"
    LaunchedEffect(count) {
        sendLog("LaunchedEffect(count): ${createText(count, innerCount)}")
    }
    LaunchedEffect(innerCount) {
        sendLog("LaunchedEffect(innerCount): ${createText(count, innerCount)}")
    }
    LaunchedEffect(Unit) {
        sendLog("LaunchedEffect(Unit): ${createText(count, innerCount)}")
    }
    SideEffect {
        sendLog("SideEffect: ${createText(count, innerCount)}")
    }
    Spacer(modifier = Modifier.padding(16.dp))
    Text(text = "innerCount")
    Counter(
        count = innerCount,
        onCountUp = { innerCount++ },
        onCountDown = { innerCount-- }
    )
    Spacer(modifier = Modifier.padding(16.dp))
    Text(text = "count: $count innerCount: $innerCount")
}