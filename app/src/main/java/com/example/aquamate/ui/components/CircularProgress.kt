import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aquamate.R
import com.example.aquamate.ui.theme.Blue100
import com.example.aquamate.ui.theme.Grey30

@Composable
fun CircularProgress(
    current: Float,
    total: Float,
    modifier: Modifier = Modifier,
    size: Dp = 150.dp,
    strokeWidth: Dp = 12.dp,
    currentTextSize: TextUnit = 24.sp,
    totalTextSize: TextUnit = 12.sp
) {
    val fraction = (current / total).coerceIn(0f, 1f)

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(size)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokePx = strokeWidth.toPx()
            drawArc(
                color = Grey30,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = strokePx)
            )
            drawArc(
                brush = Brush.sweepGradient(
                    colors = listOf(Blue100, Blue100)
                ),
                startAngle = -90f,
                sweepAngle = 360f * fraction,
                useCenter = false,
                style = Stroke(width = strokePx, cap = StrokeCap.Round)
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "${current.toInt()} ${stringResource(R.string.ml)}",
                color = Color.Black,
                fontSize = currentTextSize,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "${stringResource(R.string.of)} ${total.toInt()} ${stringResource(R.string.ml)}",
                color = Color.Black,
                fontSize = totalTextSize,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
