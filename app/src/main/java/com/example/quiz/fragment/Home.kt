package com.example.quiz.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.quiz.R


class Home : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val buttonA = view.findViewById<Button>(R.id.startGame)
        buttonA?.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_home_to_game)
        }
        setHasOptionsMenu(true)

        return view.rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.game_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about -> {
                NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}