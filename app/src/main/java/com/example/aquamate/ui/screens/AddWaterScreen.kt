import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aquamate.R
import com.example.aquamate.ui.components.AddWaterVolumeItem
import com.example.aquamate.ui.model.AddWaterViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWaterScreen(
    modifier: Modifier,
    onBack: ()-> Unit,
    onAdd: (Float) -> Unit,
    vm: AddWaterViewModel = koinViewModel()
) {

    val state by vm.uiState.collectAsState()

    var showBottomSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()

    if (showBottomSheet) {
        AddWaterVolumeItem(
            onAdd = { item -> vm.addVolume(item.value) },
            onDismiss = {
                showBottomSheet = false
            },
            onHideSheet = {
                scope.launch {
                    sheetState.hide()
                }
                showBottomSheet = false
            },
            sheetState = sheetState
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        IconButton(
            onClick = {onBack()},
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
            itemsIndexed(state) { index, amount ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
                    onClick = {
                        onAdd(amount.value)
                        onBack()
                    }
                ) {
                    Text(
                        text = "${amount.value.toInt()} ${stringResource(R.string.ml)}",
                        modifier = Modifier.padding(16.dp),
                        fontSize = 20.sp
                    )
                }
            }
        }

        Button(
            onClick = {
                showBottomSheet = true
                scope.launch {
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
