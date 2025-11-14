package cl.duoc.luditest_final.data.repository

import cl.duoc.luditest_final.data.local.PersonalityData
import cl.duoc.luditest_final.data.model.PersonalityInfo
import cl.duoc.luditest_final.data.model.PersonalityType
import cl.duoc.luditest_final.data.model.UserScore
import cl.duoc.luditest_final.data.local.TiebreakerQuestions
import cl.duoc.luditest_final.data.model.Question

class PersonalityRepository {

    // Obtiene todos los tipos de personalidad disponibles
    fun getAllPersonalityTypes(): List<PersonalityInfo> {
        return PersonalityData.personalities
    }

    // Obtiene información específica de un tipo de personalidad
    fun getPersonalityByType(type: PersonalityType): PersonalityInfo? {
        return PersonalityData.personalities.find { it.type == type }
    }

    // Calcula la personalidad dominante basado en el score del usuario
    fun calculatePersonality(userScore: UserScore): PersonalityType? {
        // Validar que tenga al menos 8 respuestas
        if (userScore.selectedAnswers.size < 8) {
            return null
        }

        // Validar que haya scores calculados
        if (userScore.scores.isEmpty()) {
            return null
        }

        // Obtener la personalidad con el score más alto
        return userScore.scores.maxByOrNull { it.value }?.key
    }

    // Verifica si hay empate entre dos o más personalidades
    fun hasTie(userScore: UserScore): Boolean {
        val maxScore = userScore.scores.values.maxOrNull() ?: return false
        return userScore.scores.count { it.value == maxScore } > 1
    }

    // Obtiene las personalidades empatadas
    fun getTiedPersonalities(userScore: UserScore): List<PersonalityType> {
        if (!hasTie(userScore)) return emptyList()
        val maxScore = userScore.scores.values.maxOrNull() ?: return emptyList()
        return userScore.scores.filter { it.value == maxScore }.keys.toList()
    }

    // Resuelve un empate usando una respuesta de desempate con peso alto
    fun resolveTieWithAnswer(userScore: UserScore, tiebreakerWeights: Map<PersonalityType, Int>): PersonalityType {
        // Crear un nuevo score sumando los pesos del desempate
        val newScores = userScore.scores.toMutableMap()
        tiebreakerWeights.forEach { (type, weight) ->
            newScores[type] = (newScores[type] ?: 0) + weight
        }

        // Devolver la personalidad con el score más alto después del desempate
        return newScores.maxByOrNull { it.value }?.key ?: PersonalityType.STEADY
    }

    // Obtiene los géneros recomendados para un tipo de personalidad
    fun getRecommendedGenres(personalityType: PersonalityType): List<String> {
        return getPersonalityByType(personalityType)?.recommendedGenres ?: emptyList()
    }

    // Obtiene las fortalezas de un tipo de personalidad
    fun getStrengths(personalityType: PersonalityType): List<String> {
        return getPersonalityByType(personalityType)?.strengths ?: emptyList()
    }
}