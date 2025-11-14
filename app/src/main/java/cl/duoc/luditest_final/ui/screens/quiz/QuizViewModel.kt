package cl.duoc.luditest_final.ui.screens.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duoc.luditest_final.data.local.QuestionData
import cl.duoc.luditest_final.data.model.Question
import cl.duoc.luditest_final.data.model.UserScore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QuizViewModel : ViewModel() {

    // Estado actual de las preguntas
    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions: StateFlow<List<Question>> = _questions.asStateFlow()

    // Pregunta actual
    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    // Score del usuario
    private val _userScore = MutableStateFlow(UserScore())
    val userScore: StateFlow<UserScore> = _userScore.asStateFlow()

    // Inicializar las preguntas
    init {
        loadQuestions()
    }

    // Cargar preguntas desde QuestionData
    private fun loadQuestions() {
        viewModelScope.launch {
            _questions.value = QuestionData.questions.shuffled()
        }
    }

    // Obtener la pregunta actual
    fun getCurrentQuestion(): Question? {
        return _questions.value.getOrNull(_currentQuestionIndex.value)
    }

    // Avanzar a la siguiente pregunta
    fun nextQuestion() {
        if (_currentQuestionIndex.value < _questions.value.size - 1) {
            _currentQuestionIndex.value++
        }
    }

    // Retroceder a la pregunta anterior
    fun previousQuestion() {
        if (_currentQuestionIndex.value > 0) {
            _currentQuestionIndex.value--
        }
    }

    // Seleccionar una respuesta
    fun selectAnswer(optionIndex: Int) {
        val currentQuestion = getCurrentQuestion()
        currentQuestion?.let { question ->
            val selectedOption = question.options.getOrNull(optionIndex)
            selectedOption?.let { option ->
                val updatedScore = _userScore.value.addAnswer(
                    questionId = question.id,
                    personalityWeights = option.personalityWeights // ← AQUÍ ESTABA EL ERROR
                )
                _userScore.value = updatedScore
            }
        }
    }

    // Verificar si es la última pregunta
    fun isLastQuestion(): Boolean {
        return _currentQuestionIndex.value == _questions.value.size - 1
    }

    // Verificar si es la primera pregunta
    fun isFirstQuestion(): Boolean {
        return _currentQuestionIndex.value == 0
    }

    // Obtener progreso
    fun getProgress(): Float {
        return if (_questions.value.isNotEmpty()) {
            (_currentQuestionIndex.value + 1).toFloat() / _questions.value.size
        } else {
            0f
        }
    }
}