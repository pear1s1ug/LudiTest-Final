package cl.duoc.luditest_final.data.model

data class UserScore(
    val scores: Map<PersonalityType, Int> = emptyMap(),
    val selectedAnswers: List<Int> = emptyList(),
    val answerWeights: List<Map<PersonalityType, Int>> = emptyList(),
    val timestamp: Long = System.currentTimeMillis()
) {
    // Número total de preguntas respondidas
    val totalQuestions: Int get() = selectedAnswers.size

    // Indica si el test está completo (mínimo 8 respuestas)
    val isComplete: Boolean get() = selectedAnswers.size >= 8

    // Agrega una nueva respuesta y actualiza los scores
    fun addAnswer(questionId: Int, personalityWeights: Map<PersonalityType, Int>): UserScore {
        val newScores = scores.toMutableMap()

        // Sumar los pesos de la respuesta a los scores acumulados
        personalityWeights.forEach { (type, weight) ->
            newScores[type] = (newScores[type] ?: 0) + weight
        }

        return this.copy(
            scores = newScores,
            selectedAnswers = selectedAnswers + questionId,
            answerWeights = answerWeights + personalityWeights
        )
    }

    // Obtiene la personalidad con el score más alto
    fun getLeadingPersonality(): PersonalityType? {
        return scores.maxByOrNull { it.value }?.key
    }

    // Verifica si hay empate entre dos o más personalidades
    fun hasTie(): Boolean {
        if (scores.size < 2) return false
        val maxScore = scores.values.maxOrNull() ?: return false
        return scores.count { it.value == maxScore } > 1
    }

    // Obtiene las personalidades empatadas
    fun getTiedPersonalities(): List<PersonalityType> {
        if (!hasTie()) return emptyList()
        val maxScore = scores.values.maxOrNull() ?: return emptyList()
        return scores.filter { it.value == maxScore }.keys.toList()
    }



}
