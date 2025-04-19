package com.example.aquamate.ui.components

import WaterVolume
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aquamate.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWaterVolumeItem(
    onAdd: (WaterVolume) -> Unit,
    onDismiss: () -> Unit,
    onHideSheet: () -> Unit,
    sheetState: SheetState
) {
    var newAmountInput by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = stringResource(R.string.write_water_volume), fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = newAmountInput,
                onValueChange = { newAmountInput = it },
                label = { Text(stringResource(R.string.ml)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val amount = newAmountInput.toIntOrNull()
                    if (amount != null) {
                        onAdd(WaterVolume(value = amount.toFloat()))
                        newAmountInput = ""

                        onHideSheet()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.add))
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

