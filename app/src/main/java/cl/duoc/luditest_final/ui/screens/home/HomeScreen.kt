package cl.duoc.luditest_final.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import cl.duoc.luditest_final.ui.theme.*
import kotlinx.coroutines.launch
import cl.duoc.luditest_final.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigateToDisclaimer: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToWishlist: () -> Unit,
    onNavigateToRecommended: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    val userState by viewModel.userState.collectAsState()

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.loadUserState()
    }

    // ✅ NUEVO: Determinar si es invitado
    val isGuest = userState.user?.hasPassword != true

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(260.dp)
                    .background(DcYellow)
                    .border(2.dp, DcBlack, RoundedCornerShape(0.dp))
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "MENU",
                    fontSize = 28.sp,
                    color = DcBlack,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Text(
                    "Hola, ${userState.user?.name ?: "Usuario"}",
                    fontSize = 18.sp,
                    color = DcBlack,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = if (isGuest) "Modo invitado" else "Cuenta registrada", // ✅ MEJORADO
                    fontSize = 12.sp,
                    color = DcGray,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                DrawerButton("Perfil de Usuario", onClick = onNavigateToProfile)
                DrawerButton("Wishlist", onClick = onNavigateToWishlist)
                DrawerButton("Juegos Recomendados", onClick = onNavigateToRecommended)

                // ✅ NUEVO: Botón dinámico según tipo de usuario
                DrawerButton(
                    text = if (isGuest) "Volver a Login" else "Cerrar Sesión", // ✅ TEXTO DINÁMICO
                    onClick = {
                        if (isGuest) {
                            // ✅ MODO INVITADO: Solo navega al login
                            scope.launch { drawerState.close() }
                            onNavigateToLogin()
                        } else {
                            // ✅ USUARIO REGISTRADO: Hace logout y navega
                            viewModel.logout()
                            scope.launch { drawerState.close() }
                            onNavigateToLogin()
                        }
                    }
                )
            }
        }
    ) {
        // ... el resto de tu código SE MANTIENE IGUAL
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DcDarkPurple),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
                    .size(40.dp)
                    .clickable {
                        scope.launch {
                            if (drawerState.isClosed) drawerState.open() else drawerState.close()
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.burger),
                    contentDescription = "Abrir menú",
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(Modifier.height(60.dp))

            Text(
                text = "BIENVENID@ DE VUELTA, ${userState.user?.name?.uppercase() ?: "AVENTURERO"}!",
                fontSize = 26.sp,
                color = DcYellow,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 20.dp)
            )

            Box(
                modifier = Modifier
                    .padding(30.dp)
                    .graphicsLayer { rotationZ = -3f }
                    .drawBehind {
                        val offset = 6.dp.toPx()
                        drawRoundRect(
                            color = DcBlack,
                            topLeft = Offset(offset, offset),
                            size = size,
                            cornerRadius = CornerRadius(12.dp.toPx())
                        )
                    }
                    .background(
                        color = DcYellow,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(2.dp, DcBlack, RoundedCornerShape(12.dp))
                    .padding(30.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Tu próximo juego favorito podría ser un lanzamiento de este año. ¿Te atreves a descubrirlo?",
                    fontSize = 22.sp,
                    color = DcBlack,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(Modifier.height(20.dp))

            val infiniteTransition = rememberInfiniteTransition(label = "zoom")
            val scale by infiniteTransition.animateFloat(
                initialValue = 1f,
                targetValue = 1.08f,
                animationSpec = infiniteRepeatable(
                    animation = tween(1000, easing = FastOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "scaleAnim"
            )

            Button(
                onClick = onNavigateToDisclaimer,
                modifier = Modifier
                    .padding(20.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    }
                    .drawBehind {
                        drawRoundRect(
                            DcBlack,
                            topLeft = Offset(4.dp.toPx(), 4.dp.toPx()),
                            size = size,
                            cornerRadius = CornerRadius(8.dp.toPx())
                        )
                    },
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(2.dp, DcBlack),
                colors = ButtonDefaults.buttonColors(containerColor = DcPurple),
                contentPadding = PaddingValues(horizontal = 60.dp, vertical = 10.dp)
            ) {
                Text(
                    "Realiza el LudiTest!",
                    color = DcWhite,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            if (userState.user?.hasCompletedTest == true) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 30.dp, vertical = 10.dp)
                        .graphicsLayer { rotationZ = -1f }
                        .drawBehind {
                            val offset = 3.dp.toPx()
                            drawRoundRect(
                                color = DcBlack,
                                topLeft = Offset(offset, offset),
                                size = size,
                                cornerRadius = CornerRadius(8.dp.toPx())
                            )
                        }
                        .background(
                            color = DcNeonGreen,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .border(2.dp, DcBlack, RoundedCornerShape(8.dp))
                        .padding(12.dp)
                ) {
                    Text(
                        text = "TEST COMPLETADO. VE A 'JUEGOS RECOMENDADOS' PARA VER TUS RESULTADOS.",
                        fontSize = 14.sp,
                        color = DcBlack,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            if (isGuest) { // ✅ USAMOS LA MISMA VARIABLE
                Spacer(Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .padding(horizontal = 30.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(DcGray.copy(alpha = 0.5f))
                        .padding(12.dp)
                ) {
                    Text(
                        text = "MODO INVITADO - Tus datos se guardarán localmente",
                        fontSize = 12.sp,
                        color = DcWhite,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun DrawerButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(2.dp, DcBlack),
        colors = ButtonDefaults.buttonColors(
            containerColor = DcWhite,
            contentColor = DcBlack
        ),
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        Text(
            text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
