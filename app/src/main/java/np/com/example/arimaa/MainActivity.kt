package np.com.example.arimaa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import np.com.example.arimaa.ui.theme.ArimaaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArimaaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArimaaBoard(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ArimaaBoard(modifier: Modifier = Modifier) {
    // Main custom composable to render the Arimaa board and initial state
    Box(
        modifier = modifier
            .fillMaxSize()
            .aspectRatio(1f) // Force to be square
    ) {
        // Render the board
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawArimaaBoard()
        }
    }
}

// Drawing the 8x8 Arimaa board
private fun DrawScope.drawArimaaBoard() {
    val boardSize = size.width
    val cellSize = boardSize / 8

    // Colors
    val lightSquareColor = Color(0xFFEED2D2)
    val darkSquareColor = Color(0xFF0C0000)
    val trapColor = Color.Red

    // Draw squares
    for (row in 0..7) {
        for (col in 0..7) {
            val squareColor = if ((row + col) % 2 == 0) lightSquareColor else darkSquareColor
            drawRect(
                color = squareColor,
                topLeft = androidx.compose.ui.geometry.Offset(col * cellSize, row * cellSize),
                size = Size(cellSize, cellSize)
            )
        }
    }

    // Draw trap squares
    val trapCoordinates = listOf(
        Pair(2, 2), Pair(2, 5),
        Pair(5, 2), Pair(5, 5)
    )
    trapCoordinates.forEach { (row, col) ->
        drawCircle(
            color = trapColor,
            radius = cellSize / 4,
            center = androidx.compose.ui.geometry.Offset(
                (col + 0.5f) * cellSize,
                (row + 0.5f) * cellSize
            )
        )
    }
}
