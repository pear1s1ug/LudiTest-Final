package cl.duoc.luditest_final.ui.screens.login

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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.duoc.luditest_final.ui.theme.*
import androidx.compose.runtime.LaunchedEffect

@Composable
fun LoginScreen(
    viewModel: LoginViewModel, // Recibir ViewModel como parámetro
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val loginState by viewModel.loginState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TertiaryContainerLight)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Botón de volver atrás
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Button(
                onClick = onNavigateBack,
                modifier = Modifier
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
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
            ) {
                Text("← Volver", color = Color.White, fontSize = 14.sp)
            }
        }

        // Título
        Box(
            modifier = Modifier
                .fillMaxWidth()
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
                text = "Iniciar Sesión",
                fontSize = 28.sp,
                color = OnSurfaceLight,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Información
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
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
                    color = BlueAccent.copy(alpha = 0.8f),
                    shape = RoundedCornerShape(10.dp)
                )
                .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "Actualmente el sistema usa login automático. Solo ingresa tu nombre para comenzar.",
                fontSize = 14.sp,
                color = Color.White,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center
            )
        }

        // Campo de nombre
        var userName by remember { mutableStateOf(TextFieldValue()) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
                .graphicsLayer { rotationZ = -1f }
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
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
                .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
                .padding(16.dp)
        ) {
            TextField(
                value = userName,
                onValueChange = { userName = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Ingresa tu nombre") },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }

        // Botón de login
        Button(
            onClick = {
                if (userName.text.isNotBlank()) {
                    viewModel.login(userName.text)
                }
            },
            enabled = userName.text.isNotBlank() && !loginState.isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
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
            Text(
                if (loginState.isLoading) "Iniciando sesión..." else "Iniciar Sesión",
                color = Color.White,
                fontSize = 18.sp
            )
        }

        // Botón para ir a registro
        Button(
            onClick = onNavigateToRegister,
            modifier = Modifier
                .fillMaxWidth()
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
            Text("Crear una cuenta nueva", color = Color.White, fontSize = 18.sp)
        }

        // Mostrar error si existe
        loginState.error?.let { error ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .graphicsLayer { rotationZ = 1f }
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
                        color = RedAccent,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .border(2.dp, Color.Black, RoundedCornerShape(8.dp))
                    .padding(12.dp)
            ) {
                Text(
                    text = error,
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

    // Navegar automáticamente cuando el login sea exitoso
    LaunchedEffect(loginState.isSuccess) {
        if (loginState.isSuccess) {
            onLoginSuccess()
        }
    }
}