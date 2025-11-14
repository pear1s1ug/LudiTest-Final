package cl.duoc.luditest_final.data.model

data class User(
    val id: String,
    val name: String,
    val email: String? = null,
    val personalityType: PersonalityType? = null,
    val userScore: UserScore? = null,
    val profileImageUrl: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val lastTestDate: Long? = null
) {
    val hasCompletedTest: Boolean get() = personalityType != null && userScore != null

    /*fun getPersonalityInfo(): PersonalityInfo? {
        return personalityType?.let { type ->

        }
    }*/

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
}

data class ProfileImageState(
    val imageUrl: String? = null,
    val isUploading: Boolean = false,
    val error: String? = null
)