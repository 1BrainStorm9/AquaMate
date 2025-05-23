package com.example.aquamate.ui.screens

import CircularProgress
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.aquamate.R
import com.example.aquamate.ui.model.MainViewModel
import com.example.aquamate.ui.navigation.Navigator
import com.example.aquamate.ui.theme.Blue100
import androidx. compose.runtime.getValue
import com.example.aquamate.ui.components.DateSelector

@Composable
fun MainScreen(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: MainViewModel,
) {

    val state by viewModel.uiState.collectAsState()
    val currentDate = viewModel.getCurrentDate()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(vertical = 20.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {

            DateSelector(
                currentDate = currentDate,
                onPreviousDay = { viewModel.moveToPreviousDay() },
                onNextDay = { viewModel.moveToNextDay() },
                onDateSelected = {date -> viewModel.setDate(date)},
                modifier = Modifier
                    .fillMaxWidth()
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                CircularProgress(
                    current = state.currentValue,
                    total = state.totalValue,
                    size = 250.dp,
                    strokeWidth = 25.dp,
                    totalTextSize = 25.sp,
                    currentTextSize = 35.sp,
                    modifier = Modifier.padding(vertical = 40.dp),
                )

                Text(
                    text = stringResource(R.string.motivation_message),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Button(
                onClick = {navController.navigate(Navigator.ADD_WATER)},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue100,
                    contentColor = Color.Black,
                    disabledContainerColor = Blue100.copy(alpha = 0.5f)
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp)
            ) {
                Text(
                    text = stringResource(R.string.add_water),
                    color = Color.Black,
                    fontSize = 20.sp,
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}
