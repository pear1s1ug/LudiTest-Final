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

    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions: StateFlow<List<Question>> = _questions.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    private val _userScore = MutableStateFlow(UserScore())
    val userScore: StateFlow<UserScore> = _userScore.asStateFlow()

    // Estado para la respuesta seleccionada en la pregunta actual
    private val _selectedAnswerIndex = MutableStateFlow<Int?>(null)
    val selectedAnswerIndex: StateFlow<Int?> = _selectedAnswerIndex.asStateFlow()

    // Estado para mostrar diálogo de confirmación
    private val _showExitDialog = MutableStateFlow(false)
    val showExitDialog: StateFlow<Boolean> = _showExitDialog.asStateFlow()

    init {
        loadQuestions()
    }

    private fun loadQuestions() {
        viewModelScope.launch {
            _questions.value = QuestionData.questions.shuffled()
        }
    }

    fun getCurrentQuestion(): Question? {
        return _questions.value.getOrNull(_currentQuestionIndex.value)
    }

    // Seleccionar respuesta sin avanzar automáticamente
    fun selectAnswer(optionIndex: Int) {
        _selectedAnswerIndex.value = optionIndex
    }

    // Avanzar a la siguiente pregunta
    fun nextQuestion() {
        if (_selectedAnswerIndex.value != null && !isLastQuestion()) {
            saveCurrentAnswer()
            _currentQuestionIndex.value++
            _selectedAnswerIndex.value = null // Resetear selección para nueva pregunta
        }
    }

    // Retroceder a la pregunta anterior
    fun previousQuestion() {
        if (!isFirstQuestion()) {
            _currentQuestionIndex.value--
            _selectedAnswerIndex.value = null // Resetear selección
        }
    }

    // Guardar la respuesta actual en el score
    private fun saveCurrentAnswer() {
        val currentQuestion = getCurrentQuestion()
        val selectedIndex = _selectedAnswerIndex.value

        currentQuestion?.let { question ->
            if (selectedIndex != null) {
                val selectedOption = question.options.getOrNull(selectedIndex)
                selectedOption?.let { option ->
                    val updatedScore = _userScore.value.addAnswer(
                        questionId = question.id,
                        personalityWeights = option.personalityWeights
                    )
                    _userScore.value = updatedScore
                }
            }
        }
    }

    // Mostrar diálogo de salida
    fun showExitConfirmation() {
        _showExitDialog.value = true
    }

    // Ocultar diálogo de salida
    fun hideExitConfirmation() {
        _showExitDialog.value = false
    }

    // Resetear el quiz completamente
    fun resetQuiz() {
        _currentQuestionIndex.value = 0
        _userScore.value = UserScore()
        _selectedAnswerIndex.value = null
        _showExitDialog.value = false
    }

    fun isLastQuestion(): Boolean {
        return _currentQuestionIndex.value == _questions.value.size - 1
    }

    fun isFirstQuestion(): Boolean {
        return _currentQuestionIndex.value == 0
    }

    fun hasAnswerSelected(): Boolean {
        return _selectedAnswerIndex.value != null
    }

    fun getProgress(): Float {
        return if (_questions.value.isNotEmpty()) {
            (_currentQuestionIndex.value + 1).toFloat() / _questions.value.size
        } else {
            0f
        }
    }
}