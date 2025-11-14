package cl.duoc.luditest_final.data.repository

import cl.duoc.luditest_final.data.local.QuestionData
import cl.duoc.luditest_final.data.local.TiebreakerQuestions
import cl.duoc.luditest_final.data.model.Question
import kotlin.random.Random

class QuestionRepository {

    fun getAllQuestions(): List<Question> {
        return QuestionData.questions
    }

    fun getShuffledQuestions(): List<Question> {
        return QuestionData.questions.shuffled().map { question ->
            question.copy(options = question.options.shuffled())
        }
    }

    fun getQuestionById(id: Int): Question? {
        // Para preguntas normales
        if (id != 100) {
            return getAllQuestions().find { it.id == id }
        }
        // Para tiebreaker
        return TiebreakerQuestions.getTiebreakerQuestion()
    }

    fun getQuestionsBatch(count: Int): List<Question> {
        return QuestionData.questions.shuffled().take(count).map { question ->
            question.copy(options = question.options.shuffled())
        }
    }

    fun getTiebreakerQuestion(): Question {
        return TiebreakerQuestions.getTiebreakerQuestion()
    }
}
