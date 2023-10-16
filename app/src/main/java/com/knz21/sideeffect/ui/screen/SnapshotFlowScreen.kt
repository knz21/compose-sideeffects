package com.knz21.sideeffect.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.knz21.sideeffect.ui.component.Counter
import com.knz21.sideeffect.ui.component.Logs

@Composable
fun SnapshotFlowScreen() {
    val state by remember { mutableStateOf(State()) }
    val logs = remember { mutableStateListOf<String>() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "count1")
        Counter(
            count = state.count1,
            onCountUp = { state.updateCount1(state.count1 + 1) },
            onCountDown = { state.updateCount1(state.count1 - 1) }
        )
        Text(text = "count2")
        Counter(
            count = state.count2,
            onCountUp = { state.updateCount2(state.count2 + 1) },
            onCountDown = { state.updateCount2(state.count2 - 1) }
        )
        SnapshotFlowComposable(
            state = state,
            sendLog = { logs.add(0, it) }
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = "logs")
        Logs(logs = logs)
    }
}

@Stable
class State {

    var count1 by mutableIntStateOf(0)

    var count2 by mutableIntStateOf(0)

    fun updateCount1(newCount: Int) {
        count1 = newCount
    }

    fun updateCount2(newCount: Int) {
        count2 = newCount
    }

    override fun toString(): String = "count1: $count1 count2: $count2"
}

@Composable
private fun SnapshotFlowComposable(
    state: State,
    sendLog: (log: String) -> Unit
) {
    LaunchedEffect(state) {
        snapshotFlow { state.count1 }.collect { count ->
            sendLog("updated count1: $count")
        }
    }
    LaunchedEffect(state) {
        snapshotFlow { state.count2 }.collect { count ->
            sendLog("updated count2: $count")
        }
    }
    Spacer(modifier = Modifier.padding(16.dp))
    Text(text = state.toString())
}