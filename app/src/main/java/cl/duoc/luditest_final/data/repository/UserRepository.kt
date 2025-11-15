package cl.duoc.luditest_final.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import cl.duoc.luditest_final.data.model.User
import cl.duoc.luditest_final.data.model.UserScore
import cl.duoc.luditest_final.data.model.PersonalityType
import cl.duoc.luditest_final.data.model.UserRegistration
import cl.duoc.luditest_final.security.SecurityConfig
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.firstOrNull

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class UserRepository(private val context: Context) {

    private val gson = Gson()

    companion object {
        // Claves para el usuario ACTUAL
        val USER_ID_KEY = stringPreferencesKey("current_user_id")
        val USER_NAME_KEY = stringPreferencesKey("current_user_name")
        val USER_EMAIL_KEY = stringPreferencesKey("current_user_email")
        val USER_PASSWORD_KEY = stringPreferencesKey("current_user_password")
        val USER_PERSONALITY_KEY = stringPreferencesKey("current_user_personality")
        val USER_SCORE_KEY = stringPreferencesKey("current_user_score")
        val USER_PROFILE_IMAGE_KEY = stringPreferencesKey("current_user_profile_image")
        val USER_CREATED_AT_KEY = longPreferencesKey("current_user_created_at")
        val USER_LAST_TEST_DATE_KEY = longPreferencesKey("current_user_last_test_date")
        val USER_EMAIL_VERIFIED_KEY = booleanPreferencesKey("current_user_email_verified")
        val IS_LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")

        // Almacenar todos los usuarios registrados
        val REGISTERED_USERS_KEY = stringPreferencesKey("registered_users")
    }

    // Flujo para observar el estado del usuario actual
    val currentUser: Flow<User?> = context.dataStore.data
        .map { preferences ->
            val id = preferences[USER_ID_KEY] ?: return@map null
            val name = preferences[USER_NAME_KEY] ?: ""
            val email = preferences[USER_EMAIL_KEY]
            val passwordHash = preferences[USER_PASSWORD_KEY]
            val personalityString = preferences[USER_PERSONALITY_KEY]
            val scoreJson = preferences[USER_SCORE_KEY]
            val profileImage = preferences[USER_PROFILE_IMAGE_KEY]
            val createdAt = preferences[USER_CREATED_AT_KEY] ?: System.currentTimeMillis()
            val lastTestDate = preferences[USER_LAST_TEST_DATE_KEY]
            val isEmailVerified = preferences[USER_EMAIL_VERIFIED_KEY] ?: false

            val personalityType = personalityString?.let {
                try {
                    PersonalityType.valueOf(it)
                } catch (e: Exception) {
                    null
                }
            }

            val userScore = scoreJson?.let {
                try {
                    gson.fromJson(it, UserScore::class.java)
                } catch (e: Exception) {
                    null
                }
            }

            User(
                id = id,
                name = name,
                email = email,
                passwordHash = passwordHash,
                personalityType = personalityType,
                userScore = userScore,
                profileImageUrl = profileImage,
                createdAt = createdAt,
                lastTestDate = lastTestDate,
                isEmailVerified = isEmailVerified
            )
        }

    val isLoggedIn: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_LOGGED_IN_KEY] ?: false
        }

    // ✅ NUEVO: Modelo para usuarios registrados
    data class RegisteredUser(
        val id: String,
        val name: String,
        val email: String,
        val passwordHash: String,
        val createdAt: Long
    )

    // ✅ NUEVO: Obtener todos los usuarios registrados
    private suspend fun getRegisteredUsers(): List<RegisteredUser> {
        val usersJson = context.dataStore.data.first()[REGISTERED_USERS_KEY]
        return if (usersJson != null) {
            try {
                gson.fromJson(usersJson, Array<RegisteredUser>::class.java).toList()
            } catch (e: Exception) {
                emptyList()
            }
        } else {
            emptyList()
        }
    }

    // ✅ NUEVO: Guardar todos los usuarios registrados
    private suspend fun saveRegisteredUsers(users: List<RegisteredUser>) {
        context.dataStore.edit { preferences ->
            preferences[REGISTERED_USERS_KEY] = gson.toJson(users)
        }
    }

    // ✅ NUEVO: Buscar usuario por email
    private suspend fun findUserByEmail(email: String): RegisteredUser? {
        val users = getRegisteredUsers()
        return users.find { it.email == email }
    }

    // ✅ NUEVO: Verificar si el email ya existe
    private suspend fun isEmailRegistered(email: String): Boolean {
        return findUserByEmail(email) != null
    }

    // ✅ CORREGIDO: Login con verificación de contraseña
    suspend fun loginWithPassword(email: String, password: String): Boolean {
        try {
            val registeredUser = findUserByEmail(email) ?: return false

            // Verificar contraseña
            val isValid = SecurityConfig.verifyPassword(password, registeredUser.passwordHash)

            if (isValid) {
                // Establecer como usuario actual
                context.dataStore.edit { preferences ->
                    preferences[USER_ID_KEY] = registeredUser.id
                    preferences[USER_NAME_KEY] = registeredUser.name
                    preferences[USER_EMAIL_KEY] = registeredUser.email
                    preferences[USER_PASSWORD_KEY] = registeredUser.passwordHash
                    preferences[IS_LOGGED_IN_KEY] = true
                    preferences[USER_CREATED_AT_KEY] = registeredUser.createdAt
                    preferences[USER_EMAIL_VERIFIED_KEY] = false
                }
                return true
            }
            return false
        } catch (e: Exception) {
            return false
        }
    }

    // ✅ CORREGIDO: Registro con contraseña
    suspend fun registerWithPassword(registration: UserRegistration): User {
        // Verificar si el email ya existe
        if (isEmailRegistered(registration.email ?: "")) {
            throw Exception("El email ya está registrado")
        }

        val userId = "user_${System.currentTimeMillis()}"
        val passwordHash = registration.password?.let {
            SecurityConfig.hashPassword(it)
        } ?: throw Exception("La contraseña es requerida")

        // Crear nuevo usuario registrado
        val newRegisteredUser = RegisteredUser(
            id = userId,
            name = registration.name,
            email = registration.email ?: "",
            passwordHash = passwordHash,
            createdAt = System.currentTimeMillis()
        )

        // Agregar a la lista de usuarios registrados
        val currentUsers = getRegisteredUsers().toMutableList()
        currentUsers.add(newRegisteredUser)
        saveRegisteredUsers(currentUsers)

        // Establecer como usuario actual
        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
            preferences[USER_NAME_KEY] = registration.name
            preferences[USER_EMAIL_KEY] = registration.email ?: ""
            preferences[USER_PASSWORD_KEY] = passwordHash
            preferences[IS_LOGGED_IN_KEY] = true
            preferences[USER_CREATED_AT_KEY] = System.currentTimeMillis()
            preferences[USER_EMAIL_VERIFIED_KEY] = false
        }

        return User(
            id = userId,
            name = registration.name,
            email = registration.email,
            passwordHash = passwordHash,
            createdAt = System.currentTimeMillis(),
            isEmailVerified = false
        )
    }

    // ✅ MANTENER: Operaciones existentes para compatibilidad
    suspend fun login(userId: String, userName: String, email: String? = null) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
            preferences[USER_NAME_KEY] = userName
            preferences[USER_EMAIL_KEY] = email ?: ""
            preferences[IS_LOGGED_IN_KEY] = true
            preferences[USER_CREATED_AT_KEY] = System.currentTimeMillis()
        }
    }

    suspend fun register(userName: String, email: String? = null): User {
        val userId = "user_${System.currentTimeMillis()}_${(1000..9999).random()}"

        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
            preferences[USER_NAME_KEY] = userName
            preferences[USER_EMAIL_KEY] = email ?: ""
            preferences[IS_LOGGED_IN_KEY] = true
            preferences[USER_CREATED_AT_KEY] = System.currentTimeMillis()
        }

        return User(
            id = userId,
            name = userName,
            email = email,
            createdAt = System.currentTimeMillis()
        )
    }

    suspend fun logout() {
        context.dataStore.edit { preferences ->
            // Limpiar solo el usuario actual, mantener los registrados
            preferences[USER_ID_KEY] = ""
            preferences[USER_NAME_KEY] = ""
            preferences[USER_EMAIL_KEY] = ""
            preferences[USER_PASSWORD_KEY] = ""
            preferences[USER_PERSONALITY_KEY] = ""
            preferences[USER_SCORE_KEY] = ""
            preferences[USER_PROFILE_IMAGE_KEY] = ""
            preferences[IS_LOGGED_IN_KEY] = false
        }
    }

    suspend fun updatePassword(newPassword: String) {
        val hashedPassword = SecurityConfig.hashPassword(newPassword)
        context.dataStore.edit { preferences ->
            preferences[USER_PASSWORD_KEY] = hashedPassword
        }
    }

    suspend fun verifyEmail() {
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL_VERIFIED_KEY] = true
        }
    }

    suspend fun updateTestResults(personalityType: PersonalityType, userScore: UserScore) {
        context.dataStore.edit { preferences ->
            preferences[USER_PERSONALITY_KEY] = personalityType.name
            preferences[USER_SCORE_KEY] = gson.toJson(userScore)
            preferences[USER_LAST_TEST_DATE_KEY] = System.currentTimeMillis()
        }
    }

    suspend fun updateProfileImage(imageUrl: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_PROFILE_IMAGE_KEY] = imageUrl
        }
    }

    suspend fun updateUserName(newName: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = newName
        }
    }

    suspend fun getCurrentUser(): User? {
        return currentUser.map { it }.first()
    }




}