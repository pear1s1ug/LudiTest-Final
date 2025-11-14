package cl.duoc.luditest_final.data.local

import cl.duoc.luditest_final.data.model.AnswerOption
import cl.duoc.luditest_final.data.model.PersonalityType
import cl.duoc.luditest_final.data.model.Question

object TiebreakerQuestions {

    fun getTiebreakerQuestion(): Question {
        return Question(
            id = 100, // ID especial para tiebreaker
            text = "En una situación donde debes elegir entre estos enfoques, ¿con cuál te identificas más?",
            options = listOf(
                AnswerOption(
                    text = "La determinación de superar desafíos y alcanzar objetivos ambiciosos",
                    personalityWeights = mapOf(PersonalityType.DOMINANT to 20)
                ),
                AnswerOption(
                    text = "La capacidad de inspirar y conectar con otros para lograr metas compartidas",
                    personalityWeights = mapOf(PersonalityType.INFLUENTIAL to 20)
                ),
                AnswerOption(
                    text = "La paciencia y consistencia para construir resultados duraderos",
                    personalityWeights = mapOf(PersonalityType.STEADY to 20)
                ),
                AnswerOption(
                    text = "La precisión y análisis meticuloso para optimizar cada decisión",
                    personalityWeights = mapOf(PersonalityType.CONSCIENTIOUS to 20)
                )
            )
        )
    }

}