package com.knz21.sideeffect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.knz21.sideeffect.ui.screen.DerivedStateOfScreen
import com.knz21.sideeffect.ui.screen.LaunchedEffectScreen
import com.knz21.sideeffect.ui.screen.ProduceStateScreen
import com.knz21.sideeffect.ui.screen.RememberUpdatedStateScreen
import com.knz21.sideeffect.ui.screen.SideEffectScreen
import com.knz21.sideeffect.ui.screen.SnapshotFlowScreen
import com.knz21.sideeffect.ui.theme.SideEffectPlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SideEffectPlaygroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    SideEffectsScreen()
                }
            }
        }
    }
}

@Composable
private fun SideEffectsScreen() {
    var current by remember { mutableStateOf(SideEffect.LaunchedEffect) }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spinner(
            current = current,
            update = { current = it }
        )
        when (current) {
            SideEffect.LaunchedEffect -> LaunchedEffectScreen()
            SideEffect.RememberUpdatedState -> RememberUpdatedStateScreen()
            SideEffect.SideEffect -> SideEffectScreen()
            SideEffect.DerivedStateOf -> DerivedStateOfScreen()
            SideEffect.SnapshotFlow -> SnapshotFlowScreen()
        }
    }
}

@Composable
private fun Spinner(current: SideEffect, update: (SideEffect) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        Text(
            modifier = Modifier
                .clickable { expanded = true }
                .padding(16.dp),
            text = current.name
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            SideEffect.values().forEach { sideEffect ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = sideEffect.name,
                            style = if (sideEffect == current) {
                                LocalTextStyle.current.copy(fontWeight = FontWeight.ExtraBold)
                            } else {
                                LocalTextStyle.current
                            }
                        )
                    },
                    onClick = {
                        update(sideEffect)
                        expanded = false
                    }
                )
            }
        }
    }
}

enum class SideEffect {
    LaunchedEffect,
    RememberUpdatedState,
    SideEffect,
    ProduceState,
    DerivedStateOf,
    SnapshotFlow
}