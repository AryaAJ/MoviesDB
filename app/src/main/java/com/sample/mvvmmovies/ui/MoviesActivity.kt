package com.sample.mvvmmovies.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.sample.mvvmmovies.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_movies.*
import kotlinx.android.synthetic.main.toolbar.*

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())
    }

    fun setToolbarText(text: String) {
        tool_title.text = text
    }
}
