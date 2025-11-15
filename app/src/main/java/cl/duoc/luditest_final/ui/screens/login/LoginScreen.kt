package cl.duoc.luditest_final.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import cl.duoc.luditest_final.R
import cl.duoc.luditest_final.ui.theme.*

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val loginState by viewModel.loginState.collectAsState()
    var email by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DcDarkPurple)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // === ICONO ===
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(DcYellow, RoundedCornerShape(25.dp))
                .padding(25.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.videogames),
                contentDescription = "Logo de la app",
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(Modifier.height(40.dp))

        // === TÍTULO CON EFECTO BORDE ===
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "LUDITEST",
                fontSize = 48.sp,
                fontWeight = FontWeight.ExtraBold,
                color = DcBlack,
                textAlign = TextAlign.Center,
                modifier = Modifier.offset(x = 4.dp, y = 4.dp)
            )
            Text(
                text = "LUDITEST",
                fontSize = 48.sp,
                fontWeight = FontWeight.ExtraBold,
                color = DcYellow,
                textAlign = TextAlign.Center
            )
        }

        Spacer(Modifier.height(12.dp))

        Text(
            text = "INICIA SESIÓN CON TU CUENTA",
            fontSize = 14.sp,
            color = DcWhite,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(40.dp))

        // === CAMPO DE EMAIL ===
        TextField(
            value = email,
            onValueChange = { email = it },
            placeholder = {
                Text(
                    "EMAIL",
                    color = Color(0xFF888888),
                    fontWeight = FontWeight.Bold
                )
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(DcGray),
            colors = TextFieldDefaults.colors(
                focusedTextColor = DcYellow,
                unfocusedTextColor = DcWhite,
                focusedContainerColor = DcGray,
                unfocusedContainerColor = DcGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = DcYellow
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Email",
                    tint = DcYellow
                )
            }
        )

        Spacer(Modifier.height(16.dp))

        // === CAMPO DE CONTRASEÑA ===
        TextField(
            value = password,
            onValueChange = { password = it },
            placeholder = {
                Text(
                    "CONTRASEÑA",
                    color = Color(0xFF888888),
                    fontWeight = FontWeight.Bold
                )
            },
            singleLine = true,
            visualTransformation = if (isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(DcGray),
            colors = TextFieldDefaults.colors(
                focusedTextColor = DcYellow,
                unfocusedTextColor = DcWhite,
                focusedContainerColor = DcGray,
                unfocusedContainerColor = DcGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = DcYellow
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Contraseña",
                    tint = DcYellow
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = { isPasswordVisible = !isPasswordVisible }
                ) {
                    Icon(
                        imageVector = if (isPasswordVisible) {
                            Icons.Default.VisibilityOff
                        } else {
                            Icons.Default.Visibility
                        },
                        contentDescription = if (isPasswordVisible) {
                            "Ocultar contraseña"
                        } else {
                            "Mostrar contraseña"
                        },
                        tint = DcYellow
                    )
                }
            }
        )

        Spacer(Modifier.height(32.dp))

        // === BOTÓN PRINCIPAL ===
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(DcYellow)
                .clickable {
                    if (email.text.isNotBlank() && password.text.isNotBlank()) {
                        viewModel.loginWithPassword(email.text, password.text)
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (loginState.isLoading) "INICIANDO SESIÓN..." else "INICIAR SESIÓN",
                fontSize = 18.sp,
                fontWeight = FontWeight.Black,
                color = DcBlack,
                letterSpacing = 1.sp
            )
        }

        Spacer(Modifier.height(24.dp))

        // === BOTÓN SECUNDARIO ===
        TextButton(
            onClick = onNavigateToRegister,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.textButtonColors(
                contentColor = DcYellow
            )
        ) {
            Text(
                "¿NO TIENES CUENTA? REGÍSTRATE",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )
        }

        Spacer(Modifier.height(16.dp))

        // === BOTÓN MODO GUEST === (✅ AHORA ES INMEDIATO)
        TextButton(
            onClick = {
                viewModel.loginAsGuest()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.textButtonColors(
                contentColor = DcNeonGreen
            )
        ) {
            Text(
                "CONTINUAR COMO INVITADO",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(Modifier.height(16.dp))

        // === FOOTER ===
        Text(
            text = "TU PERSONALIDAD, TU LEYENDA",
            fontSize = 11.sp,
            color = Color(0xFFAAAAAA),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Medium,
            letterSpacing = 2.sp
        )

        // === ERROR MESSAGE ===
        loginState.error?.let { error ->
            Spacer(Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(RedAccent)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "⚠️ $error",
                    fontSize = 14.sp,
                    color = DcWhite,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
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