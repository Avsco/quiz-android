package com.example.quiz.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.quiz.R
import com.example.quiz.databinding.FragmentGameBinding
import com.example.quiz.model.GameViewModel

class Game : Fragment(R.layout.fragment_game) {
    private lateinit var binding: FragmentGameBinding
    private val viewModel: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        viewModel.currentQuestion.observe(viewLifecycleOwner, Observer { question ->
            binding.radioGroup.clearCheck()
            binding.title.text = question.question
            val values = arrayOf(0, 1, 2, 3).toMutableList()
            values.shuffle()
            binding.first.text = question.answer[values.removeFirst()].answer
            binding.second.text = question.answer[values.removeFirst()].answer
            binding.third.text = question.answer[values.removeFirst()].answer
            binding.fourth.text = question.answer[values.removeFirst()].answer
        })

        binding.submit.setOnClickListener { view: View ->
            val checkedRadioId = binding.radioGroup.checkedRadioButtonId
            if (checkedRadioId > 0) {
                this.onClickSubmit(view, checkedRadioId)
            }
        }

        return binding.root
    }

    private fun onClickSubmit(view: View, checkedRadioId: Int) {
        var textValue = ""
        when (checkedRadioId) {
            R.id.first -> {
                textValue = binding.first.text.toString()
                true
            }
            R.id.second -> {
                textValue = binding.second.text.toString()
                true
            }
            R.id.third -> {
                textValue = binding.third.text.toString()
                true
            }
            R.id.fourth -> {
                textValue = binding.fourth.text.toString()
                true
            }
        }
        val viewCheckRadio = viewModel.currentQuestion.value?.answer?.find {
            it.answer == textValue
        }
        if (viewCheckRadio?.isWrong == false) {
            viewModel.score.value = viewModel.score.value?.plus(1)
        }
        if (viewModel.isLastQuestion() == true) {
            Navigation.findNavController(view).navigate(R.id.action_game_to_passedGame)
        } else {
            viewModel.nextQuestion()
        }
    }

}
