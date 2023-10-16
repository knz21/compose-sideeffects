package com.knz21.sideeffect.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.knz21.sideeffect.ui.component.ButtonWithLabel

@Composable
fun DerivedStateOfScreen() {
    var countA by remember { mutableIntStateOf(0) }
    var countB by remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ButtonWithLabel(
            label = "A",
            onClick = { countA++ }
        )
        ButtonWithLabel(
            label = "B",
            onClick = { countB++ }
        )
        DerivedStateOfComposable(countA, countB)
    }
}

@Composable
private fun DerivedStateOfComposable(countA: Int, countB: Int) {
    val isALessThanB1 by remember(countA, countB) {
        mutableStateOf(countA < countB)
    }
    val isALessThanB2 by remember(countA) {
        derivedStateOf { countA < countB }
    }
    val isALessThanB3 by remember(countB) {
        derivedStateOf { countA < countB }
    }
    val isALessThanB4 by remember(countA, countB) {
        derivedStateOf { countA < countB }
    }
    Text(text = "A: $countA, B: $countB A<B: ${countA < countB}")
    Spacer(modifier = Modifier.padding(16.dp))
    Text(text = "remember(countA, countB), mutableStateOf")
    Text(text = "A<B: $isALessThanB1")
    Spacer(modifier = Modifier.padding(16.dp))
    Text(text = "remember(countA), derivedStateOf")
    Text(text = "A<B: $isALessThanB2")
    Spacer(modifier = Modifier.padding(16.dp))
    Text(text = "remember(countB), derivedStateOf")
    Text(text = "A<B: $isALessThanB3")
    Spacer(modifier = Modifier.padding(16.dp))
    Text(text = "remember(countA, countB), derivedStateOf")
    Text(text = "A<B: $isALessThanB4")
}