package cl.duoc.luditest_final.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.booleanPreferencesKey
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

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class UserRepository(private val context: Context) {

    private val gson = Gson()

    companion object {
        val USER_ID_KEY = stringPreferencesKey("user_id")
        val USER_NAME_KEY = stringPreferencesKey("user_name")
        val USER_EMAIL_KEY = stringPreferencesKey("user_email")
        val USER_PASSWORD_KEY = stringPreferencesKey("user_password") // ✅ AGREGADO
        val USER_PERSONALITY_KEY = stringPreferencesKey("user_personality")
        val USER_SCORE_KEY = stringPreferencesKey("user_score")
        val USER_PROFILE_IMAGE_KEY = stringPreferencesKey("user_profile_image")
        val USER_CREATED_AT_KEY = longPreferencesKey("user_created_at")
        val USER_LAST_TEST_DATE_KEY = longPreferencesKey("user_last_test_date")
        val USER_EMAIL_VERIFIED_KEY = booleanPreferencesKey("user_email_verified") // ✅ AGREGADO
        val IS_LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")
    }

    // Flujo para observar el estado del usuario
    val currentUser: Flow<User?> = context.dataStore.data
        .map { preferences ->
            val id = preferences[USER_ID_KEY] ?: return@map null
            val name = preferences[USER_NAME_KEY] ?: ""
            val email = preferences[USER_EMAIL_KEY]
            val passwordHash = preferences[USER_PASSWORD_KEY] // ✅ AGREGADO
            val personalityString = preferences[USER_PERSONALITY_KEY]
            val scoreJson = preferences[USER_SCORE_KEY]
            val profileImage = preferences[USER_PROFILE_IMAGE_KEY]
            val createdAt = preferences[USER_CREATED_AT_KEY] ?: System.currentTimeMillis()
            val lastTestDate = preferences[USER_LAST_TEST_DATE_KEY]
            val isEmailVerified = preferences[USER_EMAIL_VERIFIED_KEY] ?: false // ✅ AGREGADO

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
                passwordHash = passwordHash, // ✅ AGREGADO
                personalityType = personalityType,
                userScore = userScore,
                profileImageUrl = profileImage,
                createdAt = createdAt,
                lastTestDate = lastTestDate,
                isEmailVerified = isEmailVerified // ✅ AGREGADO
            )
        }

    val isLoggedIn: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_LOGGED_IN_KEY] ?: false
        }

    // Login con verificación de contraseña
    suspend fun loginWithPassword(email: String, password: String): Boolean {
        val user = getCurrentUser()
        return if (user != null && user.email == email && user.passwordHash != null) {
            SecurityConfig.verifyPassword(password, user.passwordHash!!)
        } else {
            false
        }
    }

    //  Registro con contraseña
    suspend fun registerWithPassword(registration: UserRegistration): User {
        val userId = "user_${System.currentTimeMillis()}_${(1000..9999).random()}"
        val passwordHash = registration.password?.let {
            SecurityConfig.hashPassword(it)
        }

        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
            preferences[USER_NAME_KEY] = registration.name
            preferences[USER_EMAIL_KEY] = registration.email ?: ""
            preferences[USER_PASSWORD_KEY] = passwordHash ?: ""
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

    // Operaciones de login/registro existentes (mantener compatibilidad)
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
            preferences.clear()
        }
    }

    //  Cambiar contraseña
    suspend fun updatePassword(newPassword: String) {
        val hashedPassword = SecurityConfig.hashPassword(newPassword)
        context.dataStore.edit { preferences ->
            preferences[USER_PASSWORD_KEY] = hashedPassword
        }
    }

    // Verificar email
    suspend fun verifyEmail() {
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL_VERIFIED_KEY] = true
        }
    }

    // Actualizar datos del usuario existentes
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

    // Obtener usuario actual (one-shot)
    suspend fun getCurrentUser(): User? {
        return currentUser.map { it }.first()
    }
}