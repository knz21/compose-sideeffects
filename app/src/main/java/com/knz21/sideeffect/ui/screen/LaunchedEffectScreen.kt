package com.knz21.sideeffect.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.knz21.sideeffect.ui.component.Counter
import com.knz21.sideeffect.ui.component.SwitchWithLabel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LaunchedEffectScreen() {
    var flag by remember { mutableStateOf(false) }
    var number by remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SwitchWithLabel(
            label = "flag",
            checked = flag,
            onCheckedChange = { flag = it }
        )
        Counter(
            count = number,
            onCountUp = { number++ },
            onCountDown = { number-- }
        )
        LaunchedEffectComposable(
            flag = flag,
            number = number
        )
    }
}

@Composable
private fun LaunchedEffectComposable(flag: Boolean, number: Int) {
    fun createText(flag: Boolean, number: Int, count: Int) = "flag: $flag number: $number count: $count"
    var flagCount by remember { mutableIntStateOf(-1) }
    var flagText by remember { mutableStateOf(createText(flag, number, flagCount)) }
    LaunchedEffect(flag) {
        launch {
            flagCount = 0
            while (true) {
                flagCount++
                flagText = createText(flag, number, flagCount)
                delay(1000L)
            }
        }
    }
    var numberCount by remember { mutableIntStateOf(-1) }
    var numberText by remember { mutableStateOf(createText(flag, number, numberCount)) }
    LaunchedEffect(number) {
        launch {
            numberCount = 0
            while (true) {
                numberCount++
                numberText = createText(flag, number, numberCount)
                delay(1000L)
            }
        }
    }
    var flagNumberCount by remember { mutableIntStateOf(-1) }
    var flagNumberText by remember { mutableStateOf(createText(flag, number, flagNumberCount)) }
    LaunchedEffect(flag, number) {
        launch {
            flagNumberCount = 0
            while (true) {
                flagNumberCount++
                flagNumberText = createText(flag, number, flagNumberCount)
                delay(1000L)
            }
        }
    }
    var unitCount by remember { mutableIntStateOf(-1) }
    var unitText by remember { mutableStateOf(createText(flag, number, unitCount)) }
    LaunchedEffect(Unit) {
        unitCount = 0
        launch {
            while (true) {
                unitCount++
                unitText = createText(flag, number, unitCount)
                delay(1000L)
            }
        }
    }
    Column {
        Text(text = "flag: $flag")
        Text(text = "number: $number")
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = "LaunchedEffect(flag)")
        Text(text = flagText)
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = "LaunchedEffect(number)")
        Text(text = numberText)
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = "LaunchedEffect(flag, number)")
        Text(text = flagNumberText)
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = "LaunchedEffect(Unit)")
        Text(text = unitText)
    }
}