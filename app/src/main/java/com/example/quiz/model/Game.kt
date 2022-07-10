package com.example.quiz.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quiz.data.Answer
import com.example.quiz.data.Question

class GameViewModel : ViewModel() {
    private val questions = (
            arrayOf(
                Question(
                    "What isn't an activity",
                    arrayOf(
                        Answer("It's what I do when I go to the gym", true),
                        Answer("Javascript framework", true),
                        Answer("It's a view abstraction", false),
                        Answer("It's an android component", true)
                    )
                ),
                Question(
                    "What is a liveData",
                    arrayOf(
                        Answer("It's data that never changes", true),
                        Answer("It is the response of a server", true),
                        Answer("It is an observable container", false),
                        Answer("It is a design pattern", true)
                    )
                ),
                Question(
                    "Which is not a layout component in android",
                    arrayOf(
                        Answer("JsonLayout", false),
                        Answer("LinearLayout", true),
                        Answer("ConstraintLayout", true),
                        Answer("GridLayout", true)
                    )
                ),
                Question(
                    "Which is not a layout component in android",
                    arrayOf(
                        Answer("onStop", true),
                        Answer("onPause", true),
                        Answer("onResume", true),
                        Answer("onUpdate", false)
                    )
                ),
            )
            )
    private var indexCurrentQuestion = 0
    val maxScore = questions.size
    var currentQuestion = MutableLiveData(questions[indexCurrentQuestion])
    var score = MutableLiveData(0)

    fun nextQuestion() {
        indexCurrentQuestion += 1
        currentQuestion.value = (questions[indexCurrentQuestion])
    }

    fun isLastQuestion(): Boolean? {
        return currentQuestion.value?.equals(questions.last())
    }

    fun restartGame(){
        indexCurrentQuestion = 0
        score.value = 0
        currentQuestion.value = questions[indexCurrentQuestion]
    }
}

