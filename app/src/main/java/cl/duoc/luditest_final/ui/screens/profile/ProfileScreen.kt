package cl.duoc.luditest_final.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.duoc.luditest_final.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onNavigateBack: () -> Unit
) {
    val userName by viewModel.userName.collectAsState()
    val isGuest by viewModel.isGuest.collectAsState()
    val userId by viewModel.userId.collectAsState()
    val userEmail by viewModel.userEmail.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "PERFIL",
                        color = DcYellow,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver",
                            tint = DcYellow
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = DcDarkPurple
                )
            )
        },
        containerColor = DcDarkPurple
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Avatar
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(DcYellow),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Usuario",
                    tint = DcBlack,
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(Modifier.height(32.dp))

            // Nombre
            Text(
                text = userName,
                fontSize = 28.sp,
                color = DcYellow,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(16.dp))

            // Tipo de cuenta
            Text(
                text = if (isGuest) "Modo Invitado" else "Cuenta Registrada",
                fontSize = 16.sp,
                color = DcNeonGreen,
                fontWeight = FontWeight.Medium
            )

            Spacer(Modifier.height(24.dp))

            // Información adicional
            ProfileInfoItem(icon = Icons.Default.Person, title = "ID", value = userId)
            Spacer(Modifier.height(8.dp))
            ProfileInfoItem(icon = Icons.Default.Email, title = "Email", value = userEmail)

            Spacer(Modifier.height(32.dp))

            // Mensaje
            Text(
                text = if (isGuest) {
                    "Los datos se guardan localmente en este dispositivo"
                } else {
                    "Cuenta registrada - Tus datos están seguros"
                },
                fontSize = 14.sp,
                color = DcWhite,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ProfileInfoItem(icon: ImageVector, title: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = DcYellow,
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.width(12.dp))
        Column {
            Text(
                text = title,
                fontSize = 12.sp,
                color = DcWhite.copy(alpha = 0.7f)
            )
            Text(
                text = value,
                fontSize = 14.sp,
                color = DcWhite,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}