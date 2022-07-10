package com.example.quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.quiz.interfaces.OnBackPressed

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = Navigation.findNavController(this, R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.myNavHostFragment).navigateUp() || super.onSupportNavigateUp()
    }


    override fun onBackPressed() {
        val fragment =
            this.supportFragmentManager.findFragmentById(R.id.myNavHostFragment)
        (fragment as? OnBackPressed)?.onBackPressed()?.not()?.let {
            super.onBackPressed()
        }
    }

}