package cl.duoc.luditest_final.data.model

data class Question(
    val id: Int,
    val text: String,
    val options: List<AnswerOption>
)

data class AnswerOption(
    val text: String,
    val personalityWeights: Map<PersonalityType, Int>
)