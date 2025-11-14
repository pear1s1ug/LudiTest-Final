package cl.duoc.luditest_final.ui.screens.disclaimer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.duoc.luditest_final.ui.theme.TertiaryContainerLight
import cl.duoc.luditest_final.ui.theme.OnSurfaceLight
import cl.duoc.luditest_final.ui.theme.YellowAccent
import cl.duoc.luditest_final.ui.theme.PurpleAccent
import cl.duoc.luditest_final.ui.theme.GrayButton
import androidx.compose.animation.core.*

@Composable
fun DisclaimerScreen(
    onAccept: () -> Unit,
    onDecline: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "zoom")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scaleAnim"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TertiaryContainerLight)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Box(
            modifier = Modifier
                .padding(bottom = 30.dp)
                .graphicsLayer { rotationZ = -2f }
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
                    color = YellowAccent,
                    shape = RoundedCornerShape(8.dp)
                )
                .border(2.dp, Color.Black, RoundedCornerShape(8.dp))
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            Text(
                text = "Antes de Comenzar",
                fontSize = 28.sp,
                color = OnSurfaceLight,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center
            )
        }

        // Caja de contenido del disclaimer
        Box(
            modifier = Modifier
                .padding(bottom = 30.dp)
                .graphicsLayer { rotationZ = -1f }
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
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "¡Importante!",
                    fontSize = 22.sp,
                    color = OnSurfaceLight,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "Este test está basado en el modelo DISC y tiene fines recreativos. " +
                            "Los resultados son aproximaciones generales y no sustituyen una " +
                            "evaluación psicológica profesional.\n\n" +
                            "• Tus respuestas se guardarán localmente en tu dispositivo\n" +
                            "• Los juegos recomendados son sugerencias basadas en patrones generales\n" +
                            "• Puedes realizar el test cuantas veces quieras\n" +
                            "• Tu 'personalidad' puede variar en diferentes momentos",
                    fontSize = 16.sp,
                    color = OnSurfaceLight,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Justify,
                    lineHeight = 22.sp
                )
            }
        }

        // Sección de información adicional
        Box(
            modifier = Modifier
                .padding(bottom = 40.dp)
                .graphicsLayer { rotationZ = 1f }
                .drawBehind {
                    val offset = 4.dp.toPx()
                    drawRoundRect(
                        color = Color.Black,
                        topLeft = Offset(offset, offset),
                        size = size,
                        cornerRadius = CornerRadius(10.dp.toPx())
                    )
                }
                .background(
                    color = PurpleAccent.copy(alpha = 0.8f),
                    shape = RoundedCornerShape(10.dp)
                )
                .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
                .padding(20.dp)
        ) {
            Text(
                text = "💡 El test toma aproximadamente 5 minutos y consta de 12 preguntas de opción múltiple",
                fontSize = 14.sp,
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center
            )
        }

        // Botones de acción
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Botón Cancelar
            Button(
                onClick = onDecline,
                modifier = Modifier
                    .weight(1f)
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
                colors = ButtonDefaults.buttonColors(GrayButton),
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                Text("Cancelar", color = Color.White, fontSize = 18.sp)
            }

            // Botón Aceptar (con animación)
            Button(
                onClick = onAccept,
                modifier = Modifier
                    .weight(1f)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    }
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
                colors = ButtonDefaults.buttonColors(PurpleAccent),
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                Text("Aceptar y Continuar", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}