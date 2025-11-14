package cl.duoc.luditest_final.ui.screens.home

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.duoc.luditest_final.ui.theme.TertiaryContainerLight
import cl.duoc.luditest_final.ui.theme.OnSurfaceLight
import cl.duoc.luditest_final.ui.theme.YellowAccent
import cl.duoc.luditest_final.ui.theme.PurpleAccent
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToDisclaimer: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToWishlist: () -> Unit,
    onNavigateToRecommended: () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(260.dp)
                    .background(YellowAccent)
                    .border(2.dp, Color.Black)
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Menú",
                    fontSize = 28.sp,
                    color = Color.Black,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                DrawerButton("Perfil de Usuario", onClick = onNavigateToProfile)
                DrawerButton("Wishlist", onClick = onNavigateToWishlist)
                DrawerButton("Juegos Recomendados", onClick = onNavigateToRecommended)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(TertiaryContainerLight),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(40.dp))

            // Botón hamburguesa
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
                Text("☰", fontSize = 24.sp)
            }

            Spacer(Modifier.height(100.dp))

            // Caja de texto principal
            Box(
                modifier = Modifier
                    .padding(30.dp)
                    .graphicsLayer { rotationZ = -3f }
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
                    .padding(30.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Tu próximo juego favorito podría ser un lanzamiento de este año. ¿Te atreves a descubrirlo?",
                    fontSize = 22.sp,
                    color = OnSurfaceLight,
                    fontFamily = FontFamily.SansSerif
                )
            }

            Spacer(Modifier.height(10.dp))

            // Icono del control de juego
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Color.Gray, RoundedCornerShape(16.dp))
            ) {
                Text(
                    "🎮",
                    fontSize = 50.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(Modifier.height(20.dp))

            // Botón animado
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
                            Color.Black,
                            topLeft = Offset(4.dp.toPx(), 4.dp.toPx()),
                            size = size,
                            cornerRadius = CornerRadius(8.dp.toPx())
                        )
                    },
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(2.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(PurpleAccent),
                contentPadding = PaddingValues(horizontal = 60.dp, vertical = 10.dp)
            ) {
                Text("¡Realiza el LudiTest!", color = Color.White, fontSize = 26.sp)
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
        border = BorderStroke(2.dp, Color.Black),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        Text(text, fontSize = 18.sp)
    }
}