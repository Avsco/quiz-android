package com.example.quiz.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.quiz.R
import com.example.quiz.databinding.FragmentGameBinding
import com.example.quiz.model.GameViewModel

class Game : Fragment(R.layout.fragment_game) {
    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        viewModel = ViewModelProvider(activity!!)[GameViewModel::class.java]

        viewModel.currentQuestion.observe(viewLifecycleOwner, Observer { question ->
            binding.radioGroup.clearCheck()
            binding.title.text = question.question
            binding.first.text = question.answer[0].answer
            binding.second.text = question.answer[1].answer
            binding.third.text = question.answer[2].answer
            binding.fourth.text = question.answer[3].answer
        })

        binding.submit.setOnClickListener { view : View ->
            val checkedRadioId = binding.radioGroup.checkedRadioButtonId
            if (checkedRadioId > 0) {
                this.onClickSubmit(view, checkedRadioId)
            }
        }

        return binding.root
    }

    private fun onClickSubmit(view: View, checkedRadioId: Int) {
        val checkedRadio = view?.findViewById<RadioButton>(checkedRadioId)
        val viewCheckRadio = viewModel.currentQuestion.value?.answer?.find {
            it.answer == checkedRadio?.text
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
