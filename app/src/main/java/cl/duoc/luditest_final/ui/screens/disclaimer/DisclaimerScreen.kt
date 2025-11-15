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
import cl.duoc.luditest_final.ui.theme.*
import androidx.compose.animation.core.*
import androidx.compose.ui.text.font.FontWeight

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
            .background(DcDarkPurple) // ✅ Usando color del theme
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
                        color = DcBlack, // ✅ Usando color del theme
                        topLeft = Offset(offset, offset),
                        size = size,
                        cornerRadius = CornerRadius(8.dp.toPx())
                    )
                }
                .background(
                    color = DcYellow, // ✅ Usando color del theme
                    shape = RoundedCornerShape(8.dp)
                )
                .border(2.dp, DcBlack, RoundedCornerShape(8.dp)) // ✅ Usando color del theme
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            Text(
                text = "ANTES DE COMENZAR",
                fontSize = 28.sp,
                color = DcBlack, // ✅ Usando color del theme
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
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
                        color = DcBlack, // ✅ Usando color del theme
                        topLeft = Offset(offset, offset),
                        size = size,
                        cornerRadius = CornerRadius(12.dp.toPx())
                    )
                }
                .background(
                    color = DcGray, // ✅ Usando color del theme
                    shape = RoundedCornerShape(12.dp)
                )
                .border(2.dp, DcBlack, RoundedCornerShape(12.dp)) // ✅ Usando color del theme
                .padding(24.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "⚡ ¡IMPORTANTE!",
                    fontSize = 22.sp,
                    color = DcYellow, // ✅ Usando color del theme
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
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
                    color = DcWhite, // ✅ Usando color del theme
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
                        color = DcBlack, // ✅ Usando color del theme
                        topLeft = Offset(offset, offset),
                        size = size,
                        cornerRadius = CornerRadius(10.dp.toPx())
                    )
                }
                .background(
                    color = DcPurple, // ✅ Usando color del theme
                    shape = RoundedCornerShape(10.dp)
                )
                .border(2.dp, DcBlack, RoundedCornerShape(10.dp)) // ✅ Usando color del theme
                .padding(20.dp)
        ) {
            Text(
                text = "EL TEST TOMA APROXIMADAMENTE 5 MINUTOS\nY CONSTA DE 12 PREGUNTAS DE OPCIÓN MÚLTIPLE",
                fontSize = 14.sp,
                color = DcBlack, // ✅ Usando color del theme
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
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
                            DcBlack, // ✅ Usando color del theme
                            topLeft = Offset(3.dp.toPx(), 3.dp.toPx()),
                            size = size,
                            cornerRadius = CornerRadius(6.dp.toPx())
                        )
                    },
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(2.dp, DcBlack), // ✅ Usando color del theme
                colors = ButtonDefaults.buttonColors(containerColor = DcGray), // ✅ Usando color del theme
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                Text("CANCELAR", color = DcWhite, fontSize = 18.sp, fontWeight = FontWeight.Bold) // ✅ Usando color del theme
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
                            DcBlack, // ✅ Usando color del theme
                            topLeft = Offset(4.dp.toPx(), 4.dp.toPx()),
                            size = size,
                            cornerRadius = CornerRadius(6.dp.toPx())
                        )
                    },
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(2.dp, DcBlack), // ✅ Usando color del theme
                colors = ButtonDefaults.buttonColors(containerColor = DcYellow), // ✅ Usando color del theme
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                Text("¡ACEPTAR!", color = DcBlack, fontSize = 18.sp, fontWeight = FontWeight.Bold) // ✅ Usando color del theme
            }
        }
    }
}