package com.sample.assignment.datatest

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sample.assignment.util.CoroutineRule
import com.sample.mvvmmovies.FakeRepository
import com.sample.mvvmmovies.models.moviedb.DiscoverMovieResponse
import com.sample.mvvmmovies.models.moviedb.Result
import com.sample.mvvmmovies.ui.fragments.discover.DiscoverMoviesViewModel
import com.sample.mvvmmovies.util.NetworkManager.hasInternetConnection
import com.sample.mvvmmovies.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.Response

/**
Created by Ajay Arya on 11/02/21
 */

@ExperimentalCoroutinesApi
class DiscoverListTest {
    @get: Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = CoroutineRule()

    @Mock
    private lateinit var context: Context

    private var listRepository: FakeRepository = FakeRepository()
    lateinit var listViewModel: DiscoverMoviesViewModel

    private lateinit var mainList: DiscoverMovieResponse
    private lateinit var onRenderListObserver: Observer<Resource<DiscoverMovieResponse>>
    private lateinit var onRenderErrorListObserver: Observer<String>

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)
        `when`<Context>(context.applicationContext).thenReturn(context)
        `when`(hasInternetConnection(context)).thenReturn(true)

        listViewModel = DiscoverMoviesViewModel(context, listRepository)

        mockResponse()
        setupObservers()
    }

    @Test
    fun getListFromRepository() {

        testCoroutineRule.runBlockingTest {
            `when`(listRepository.getDiscoverMovie(1)).thenReturn(
                Response.success(mainList)
            )
            `when`<Context>(context.applicationContext).thenReturn(context)

            with(listViewModel) {
                listViewModel.discoverMovies()
                listViewModel.discoverMovies.observeForever(onRenderListObserver)
            }

            delay(1000)

            verify(onRenderListObserver).onChanged(listViewModel.discoverMovies.value)

            assertEquals(
                listViewModel.discoverMovies.value,
                mainList
            )
        }
    }

    private fun setupObservers() {
        onRenderListObserver =
            mock(Observer::class.java) as Observer<Resource<DiscoverMovieResponse>>
        onRenderErrorListObserver = mock(Observer::class.java) as Observer<String>
    }

    fun mockResponse() {
        val tempList =
            DiscoverMovieResponse(
                1,
                mutableListOf(
                    Result(
                        1, false, "/sss", emptyList(), "en", "Wonder Women",
                        "", 7.0, "/www", "", "Wonder Women", false,
                        7f, ""
                    ), Result(
                        2, false, "/sss", emptyList(), "en", "Batman",
                        "", 7.0, "/www", "", "Batman", false,
                        9f, ""
                    )
                ), 1, 1
            )

        mainList = tempList
    }
}
