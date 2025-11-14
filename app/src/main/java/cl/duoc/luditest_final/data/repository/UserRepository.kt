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
        val USER_PERSONALITY_KEY = stringPreferencesKey("user_personality")
        val USER_SCORE_KEY = stringPreferencesKey("user_score")
        val USER_PROFILE_IMAGE_KEY = stringPreferencesKey("user_profile_image")
        val USER_CREATED_AT_KEY = longPreferencesKey("user_created_at")
        val USER_LAST_TEST_DATE_KEY = longPreferencesKey("user_last_test_date")
        val IS_LOGGED_IN_KEY = booleanPreferencesKey("is_logged_in")
    }

    // Flujo para observar el estado del usuario
    val currentUser: Flow<User?> = context.dataStore.data
        .map { preferences ->
            val id = preferences[USER_ID_KEY] ?: return@map null
            val name = preferences[USER_NAME_KEY] ?: ""
            val email = preferences[USER_EMAIL_KEY]
            val personalityString = preferences[USER_PERSONALITY_KEY]
            val scoreJson = preferences[USER_SCORE_KEY]
            val profileImage = preferences[USER_PROFILE_IMAGE_KEY]
            val createdAt = preferences[USER_CREATED_AT_KEY] ?: System.currentTimeMillis()
            val lastTestDate = preferences[USER_LAST_TEST_DATE_KEY]

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
                personalityType = personalityType,
                userScore = userScore,
                profileImageUrl = profileImage,
                createdAt = createdAt,
                lastTestDate = lastTestDate
            )
        }

    val isLoggedIn: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_LOGGED_IN_KEY] ?: false
        }

    // Operaciones de login/registro
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

    // Actualizar datos del usuario
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