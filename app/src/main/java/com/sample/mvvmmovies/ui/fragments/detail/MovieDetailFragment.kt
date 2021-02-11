package com.sample.mvvmmovies.ui.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sample.mvvmmovies.R
import com.sample.mvvmmovies.databinding.FragmentMovieBinding
import com.sample.mvvmmovies.ui.MoviesActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie.*

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    val viewModel: MovieDetailViewModel by viewModels()
    private lateinit var bind: FragmentMovieBinding
    val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movie, container, false
        )
        val mRootView = bind.root
        bind.lifecycleOwner = this
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = args.movie
        fab.isVisible = args.showFab

        bind.movie = movie

        fab.setOnClickListener {
            viewModel.saveArticle(movie)
            Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MoviesActivity?)?.setToolbarText("Movie Detail")
    }
}