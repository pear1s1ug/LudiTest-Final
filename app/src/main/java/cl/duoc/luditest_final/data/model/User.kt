package cl.duoc.luditest_final.data.model

data class User(
    val id: String,
    val name: String,
    val email: String? = null,
    val passwordHash: String? = null, // ✅ AGREGADO: Hash de contraseña
    val personalityType: PersonalityType? = null,
    val userScore: UserScore? = null,
    val profileImageUrl: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val lastTestDate: Long? = null,
    val isEmailVerified: Boolean = false // ✅ AGREGADO: Verificación de email
) {
    val hasCompletedTest: Boolean get() = personalityType != null && userScore != null
    val hasPassword: Boolean get() = !passwordHash.isNullOrEmpty()

    fun updateProfileImage(newImageUrl: String): User {
        return this.copy(profileImageUrl = newImageUrl)
    }

    fun updateTestResults(
        newPersonalityType: PersonalityType,
        newUserScore: UserScore
    ): User {
        return this.copy(
            personalityType = newPersonalityType,
            userScore = newUserScore,
            lastTestDate = System.currentTimeMillis()
        )
    }

    fun updatePassword(newPasswordHash: String): User {
        return this.copy(passwordHash = newPasswordHash)
    }

    fun verifyEmail(): User {
        return this.copy(isEmailVerified = true)
    }
}

data class ProfileImageState(
    val imageUrl: String? = null,
    val isUploading: Boolean = false,
    val error: String? = null
)

data class UserRegistration(
    val name: String,
    val email: String? = null,
    val password: String? = null
)