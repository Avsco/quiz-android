package com.example.quiz.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.quiz.R
import com.example.quiz.databinding.FragmentPassedGameBinding
import com.example.quiz.interfaces.OnBackPressed
import com.example.quiz.model.GameViewModel


class PassedGame : Fragment(R.layout.fragment_passed_game), OnBackPressed {
    private lateinit var binding: FragmentPassedGameBinding

    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_passed_game, container, false)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        viewModel = ViewModelProvider(activity!!)[GameViewModel::class.java]

        Log.d("Game", viewModel.score.value.toString())
        if (viewModel.score.value?.equals(viewModel.maxScore) == false) {
            binding.textGame.text = "Try Again"
            binding.restartGame.text = "Try Again?"
            binding.layoutPassedGame.setBackgroundResource(R.color.red)
        }

        binding.restartGame.setOnClickListener { view: View ->
            viewModel.restartGame()
            Navigation.findNavController(view).popBackStack()
        }

        return binding.root
    }

    override fun onBackPressed(): Boolean {
        viewModel.restartGame()
        return false
    }
}