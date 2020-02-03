package com.anesabml.quotey.ui.main

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.anesabml.quotey.EspressoTestsMatchers.withDrawable
import com.anesabml.quotey.R
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
@MediumTest
class MainFragmentTest {


    @Test
    fun addToFavorite_favoriteButtonChanges() {
        val scenario = launchFragmentInContainer<MainFragment>(Bundle(), R.style.AppTheme)

        sleep(2000)

        onView(withId(R.id.add_to_favorite_button)).check(matches(withDrawable(R.drawable.ic_star)))

        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))

//        onView(withId(R.id.add_to_favorite_button)).perform(click())

    }

    private fun <T : ViewModel> createViewModelFactory(viewModel: T): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(viewModelClass: Class<T>): T {
                if (viewModelClass.isAssignableFrom(viewModel.javaClass)) {
                    @Suppress("UNCHECKED_CAST")
                    return viewModel as T
                }
                throw IllegalArgumentException("Unknown view model class $viewModelClass")
            }
        }
    }
}