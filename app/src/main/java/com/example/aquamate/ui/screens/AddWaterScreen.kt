import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.aquamate.R
import com.example.aquamate.ui.model.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWaterScreen(navController: NavHostController, viewModel: MainViewModel = viewModel()) {
    var waterAmounts by remember { mutableStateOf(listOf(250, 500, 750)) }
    var newAmountInput by remember { mutableStateOf("") }

    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
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
                            waterAmounts = waterAmounts + amount
                            newAmountInput = ""

                            coroutineScope.launch {
                                sheetState.hide()
                                showSheet = false
                            }
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        IconButton(
            onClick = {navController.popBackStack()},
            modifier = Modifier.offset(x = (-12).dp)
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }

        Text(stringResource(R.string.select_water_volume), fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            itemsIndexed(waterAmounts) { index, amount ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
                    onClick = {
                        Log.d("!!!", "Btn Press")
                        viewModel.addWater(amount)
                    }
                ) {
                    Text(
                        text = "$amount ${stringResource(R.string.ml)}",
                        modifier = Modifier.padding(16.dp),
                        fontSize = 20.sp
                    )
                }
            }
        }

        Button(
            onClick = {
                showSheet = true
                coroutineScope.launch {
                    sheetState.show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(stringResource(R.string.add_volume), fontSize = 18.sp)
        }
    }
}
