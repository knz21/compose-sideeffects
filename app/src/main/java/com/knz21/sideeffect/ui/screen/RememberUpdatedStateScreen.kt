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
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.knz21.sideeffect.ui.component.Counter
import com.knz21.sideeffect.ui.component.SwitchWithLabel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RememberUpdatedStateScreen() {
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
        RememberUpdatedStateComposabletent(
            flag = flag,
            number = number
        )
    }
}

@Composable
private fun RememberUpdatedStateComposabletent(flag: Boolean, number: Int) {
    fun createText(flag: Boolean, number: Int, count: Int) = "flag: $flag number: $number count: $count"
    var count1 by remember { mutableIntStateOf(-1) }
    var text1 by remember { mutableStateOf(createText(flag, number, count1)) }
    LaunchedEffect(flag, number) {
        launch {
            count1 = 0
            while (true) {
                count1++
                text1 = createText(flag, number, count1)
                delay(1000L)
            }
        }
    }
    var count2 by remember { mutableIntStateOf(-1) }
    var text2 by remember { mutableStateOf(createText(flag, number, count2)) }
    val newNumber by rememberUpdatedState(newValue = number)
    LaunchedEffect(flag) {
        count2 = 0
        launch {
            while (true) {
                count2++
                text2 = createText(flag, newNumber, count2)
                delay(1000L)
            }
        }
    }
    Column {
        Text(text = "flag: $flag")
        Text(text = "count: $number")
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = "LaunchedEffect(flag, number)")
        Text(text = text1)
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = "LaunchedEffect(flag), rememberUpdatedState(newValue = number)")
        Text(text = text2)
    }
}