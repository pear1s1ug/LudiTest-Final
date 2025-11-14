package cl.duoc.luditest_final.ui.screens.quiz

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.duoc.luditest_final.ui.theme.*
import cl.duoc.luditest_final.ui.screens.quiz.QuizViewModel

@Composable
fun QuizScreen() {
    val viewModel: QuizViewModel = viewModel()
    val questions by viewModel.questions.collectAsState()
    val currentQuestionIndex by viewModel.currentQuestionIndex.collectAsState()

    val currentQuestion = viewModel.getCurrentQuestion()
    val totalQuestions = questions.size

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TertiaryContainerLight)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header con progreso
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
                .graphicsLayer { rotationZ = -1f }
                .drawBehind {
                    val offset = 4.dp.toPx()
                    drawRoundRect(
                        color = Color.Black,
                        topLeft = Offset(offset, offset),
                        size = size,
                        cornerRadius = CornerRadius(8.dp.toPx())
                    )
                }
                .background(
                    color = BlueAccent,
                    shape = RoundedCornerShape(8.dp)
                )
                .border(2.dp, Color.Black, RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "Pregunta ${currentQuestionIndex + 1} de $totalQuestions",
                fontSize = 20.sp,
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Mostrar pregunta actual
        currentQuestion?.let { question ->
            // Pregunta
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp)
                    .graphicsLayer { rotationZ = 1f }
                    .drawBehind {
                        val offset = 6.dp.toPx()
                        drawRoundRect(
                            color = Color.Black,
                            topLeft = Offset(offset, offset),
                            size = size,
                            cornerRadius = CornerRadius(12.dp.toPx())
                        )
                    }
                    .background(
                        color = YellowAccent,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(2.dp, Color.Black, RoundedCornerShape(12.dp))
                    .padding(24.dp)
            ) {
                Text(
                    text = question.text,
                    fontSize = 18.sp,
                    color = OnSurfaceLight,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Center,
                    lineHeight = 24.sp
                )
            }

            // Opciones de respuesta
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                question.options.forEachIndexed { index, option ->
                    OptionButton(
                        text = option.text,
                        onClick = {
                            viewModel.selectAnswer(index)
                            if (!viewModel.isLastQuestion()) {
                                viewModel.nextQuestion()
                            }
                        }
                    )
                }
            }

            // Botones de navegación
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Botón Atrás
                Button(
                    onClick = { viewModel.previousQuestion() },
                    enabled = !viewModel.isFirstQuestion(),
                    modifier = Modifier
                        .weight(0.45f)
                        .drawBehind {
                            drawRoundRect(
                                Color.Black,
                                topLeft = Offset(3.dp.toPx(), 3.dp.toPx()),
                                size = size,
                                cornerRadius = CornerRadius(6.dp.toPx())
                            )
                        },
                    shape = RoundedCornerShape(5.dp),
                    border = BorderStroke(2.dp, Color.Black),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (viewModel.isFirstQuestion()) GrayButton.copy(alpha = 0.5f) else GrayButton
                    ),
                    contentPadding = PaddingValues(vertical = 12.dp)
                ) {
                    Text("Atrás", color = Color.White, fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Botón Siguiente
                Button(
                    onClick = { viewModel.nextQuestion() },
                    enabled = !viewModel.isLastQuestion(),
                    modifier = Modifier
                        .weight(0.45f)
                        .drawBehind {
                            drawRoundRect(
                                Color.Black,
                                topLeft = Offset(4.dp.toPx(), 4.dp.toPx()),
                                size = size,
                                cornerRadius = CornerRadius(6.dp.toPx())
                            )
                        },
                    shape = RoundedCornerShape(5.dp),
                    border = BorderStroke(2.dp, Color.Black),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (viewModel.isLastQuestion()) PurpleAccent.copy(alpha = 0.5f) else PurpleAccent
                    ),
                    contentPadding = PaddingValues(vertical = 12.dp)
                ) {
                    Text(
                        if (viewModel.isLastQuestion()) "Finalizar" else "Siguiente",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        } ?: run {
            // Si no hay pregunta actual (caso de error)
            Text(
                "Cargando preguntas...",
                fontSize = 18.sp,
                color = OnSurfaceLight
            )
        }
    }
}

@Composable
fun OptionButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                drawRoundRect(
                    Color.Black,
                    topLeft = Offset(3.dp.toPx(), 3.dp.toPx()),
                    size = size,
                    cornerRadius = CornerRadius(8.dp.toPx())
                )
            },
        shape = RoundedCornerShape(6.dp),
        border = BorderStroke(2.dp, Color.Black),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = OnSurfaceLight
        ),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 20.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
    }
}