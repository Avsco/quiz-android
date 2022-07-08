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
                    "First question",
                    arrayOf(
                        Answer("First answer", true),
                        Answer("second answer", true),
                        Answer("third answer", false),
                        Answer("fourth answer", true)
                    )
                ),
                Question(
                    "Second question",
                    arrayOf(
                        Answer("First answer", true),
                        Answer("second answer", true),
                        Answer("third answer", false),
                        Answer("fourth answer", true)
                    )
                ),
                Question(
                    "Third question",
                    arrayOf(
                        Answer("First answer", false),
                        Answer("second answer", true),
                        Answer("third answer", true),
                        Answer("fourth answer", true)
                    )
                ),
                Question(
                    "Fourth question",
                    arrayOf(
                        Answer("First answer", true),
                        Answer("second answer", true),
                        Answer("third answer", true),
                        Answer("fourth answer", false)
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

