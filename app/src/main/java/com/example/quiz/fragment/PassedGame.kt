package com.example.quiz.fragment

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.quiz.R
import com.example.quiz.databinding.FragmentPassedGameBinding
import com.example.quiz.model.GameViewModel

class PassedGame : Fragment(R.layout.fragment_passed_game) {
    private lateinit var binding: FragmentPassedGameBinding

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_passed_game, container, false)
        viewModel = ViewModelProvider(activity!!)[GameViewModel::class.java]

        Log.d("Game", viewModel.score.value.toString())
        if(viewModel.score.value?.equals(viewModel.maxScore) == false){
            binding.textGame.text = "Try Again"
            binding.restartGame.text= "Try Again?"
            binding.layoutPassedGame.setBackgroundResource(R.color.red)
        }

        binding.restartGame.setOnClickListener { view: View ->
            viewModel.restartGame()
            Navigation.findNavController(view).navigate(R.id.action_passedGame_to_game)
        }

        return binding.root
    }
}