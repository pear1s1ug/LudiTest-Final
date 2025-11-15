package cl.duoc.luditest_final.data.model

data class PersonalityInfo(
    val type: PersonalityType,
    val title: String,
    val description: String,
    val strengths: List<String>,
    val recommendedGenres: List<GameGenre>
)