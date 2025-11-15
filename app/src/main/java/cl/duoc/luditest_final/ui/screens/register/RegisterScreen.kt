package cl.duoc.luditest_final.ui.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
fun RegisterScreen(
    viewModel: RegisterViewModel,
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val registerState by viewModel.registerState.collectAsState()

    var name by remember { mutableStateOf(TextFieldValue()) }
    var email by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue()) }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DcDarkPurple)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // === BOTÓN VOLVER ===
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                onClick = onNavigateBack,
                modifier = Modifier
                    .size(40.dp)
                    .background(DcGray, RoundedCornerShape(10.dp))
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver",
                    tint = DcWhite
                )
            }
        }

        // === ICONO ===
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(DcYellow, RoundedCornerShape(20.dp))
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.videogames),
                contentDescription = "Logo de la app",
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(Modifier.height(30.dp))

        // === TÍTULO ===
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            // Texto sombra (borde)
            Text(
                text = "CREAR CUENTA",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = DcBlack,
                textAlign = TextAlign.Center,
                modifier = Modifier.offset(x = 3.dp, y = 3.dp)
            )
            // Texto principal
            Text(
                text = "CREAR CUENTA",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = DcYellow,
                textAlign = TextAlign.Center
            )
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = "ÚNETE A LA AVENTURA GAMER",
            fontSize = 14.sp,
            color = DcWhite,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(40.dp))

        // === CAMPO DE NOMBRE ===
        TextField(
            value = name,
            onValueChange = { name = it },
            placeholder = {
                Text(
                    "NOMBRE DE USUARIO",
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
                    contentDescription = "Nombre",
                    tint = DcYellow
                )
            }
        )

        Spacer(Modifier.height(16.dp))

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
                    imageVector = Icons.Default.Email,
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

        Spacer(Modifier.height(16.dp))

        // === CAMPO DE CONFIRMAR CONTRASEÑA ===
        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            placeholder = {
                Text(
                    "CONFIRMAR CONTRASEÑA",
                    color = Color(0xFF888888),
                    fontWeight = FontWeight.Bold
                )
            },
            singleLine = true,
            visualTransformation = if (isConfirmPasswordVisible) {
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
                    contentDescription = "Confirmar contraseña",
                    tint = DcYellow
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = { isConfirmPasswordVisible = !isConfirmPasswordVisible }
                ) {
                    Icon(
                        imageVector = if (isConfirmPasswordVisible) {
                            Icons.Default.VisibilityOff
                        } else {
                            Icons.Default.Visibility
                        },
                        contentDescription = if (isConfirmPasswordVisible) {
                            "Ocultar contraseña"
                        } else {
                            "Mostrar contraseña"
                        },
                        tint = DcYellow
                    )
                }
            }
        )

        // === VALIDACIÓN DE CONTRASEÑA ===
        if (password.text.isNotBlank() && confirmPassword.text.isNotBlank() && password.text != confirmPassword.text) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = "⚠️ LAS CONTRASEÑAS NO COINCIDEN",
                fontSize = 12.sp,
                color = RedAccent,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(Modifier.height(32.dp))

        // === BOTÓN DE REGISTRO ===
        val isFormValid = name.text.isNotBlank() &&
                email.text.isNotBlank() &&
                password.text.isNotBlank() &&
                confirmPassword.text.isNotBlank() &&
                password.text == confirmPassword.text

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(
                    if (isFormValid) DcYellow else DcGray.copy(alpha = 0.5f)
                )
                .clickable(
                    enabled = isFormValid && !registerState.isLoading
                ) {
                    if (isFormValid) {
                        viewModel.register(
                            name = name.text,
                            email = email.text,
                            password = password.text
                        )
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (registerState.isLoading) "CREANDO CUENTA..." else "CREAR CUENTA",
                fontSize = 18.sp,
                fontWeight = FontWeight.Black,
                color = if (isFormValid) DcBlack else Color(0xFF666666),
                letterSpacing = 1.sp
            )
        }

        Spacer(Modifier.height(24.dp))

        // === BOTÓN SECUNDARIO ===
        TextButton(
            onClick = onNavigateToLogin,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.textButtonColors(
                contentColor = DcYellow
            )
        ) {
            Text(
                "¿YA TIENES CUENTA? INICIA SESIÓN",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )
        }

        Spacer(Modifier.height(16.dp))

        // === INFORMACIÓN DE SEGURIDAD ===
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(DcNeonGreen.copy(alpha = 0.2f))
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = "🔒 TU SEGURIDAD ES IMPORTANTE",
                    fontSize = 12.sp,
                    color = DcNeonGreen,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "• Tu contraseña se encripta de forma segura\n" +
                            "• Nunca compartimos tus datos\n" +
                            "• Puedes eliminar tu cuenta cuando quieras",
                    fontSize = 10.sp,
                    color = DcWhite,
                    lineHeight = 14.sp
                )
            }
        }

        // === MENSAJE DE ERROR ===
        registerState.error?.let { error ->
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

    // Navegar automáticamente cuando el registro sea exitoso
    LaunchedEffect(registerState.isSuccess) {
        if (registerState.isSuccess) {
            onRegisterSuccess()
        }
    }
}