package com.anesabml.quotey.ui

import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.anesabml.quotey.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get: Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    private lateinit var bottomNavigation: BottomNavigationView
    private val mockedListener =
        mock(BottomNavigationView.OnNavigationItemSelectedListener::class.java)

    @Before
    fun setup() {
        bottomNavigation =
            activityTestRule.activity.findViewById(R.id.bottom_nav_view)

        bottomNavigation.setOnNavigationItemSelectedListener(mockedListener)
    }

    @Test
    fun test_isBottomNavigationDisplayed() {
        onView(withId(R.id.bottom_nav_view))
            .check(matches(isDisplayed()))

        val menuItems = bottomNavigation.menu
        assertThat(3, `is`(menuItems.size()))

    }

    @Test
    fun test_isFavoriteFragmentDisplayed() {
        val menuItems = bottomNavigation.menu

        `when`(mockedListener.onNavigationItemSelected(menuItems.findItem(R.id.favorites)))
            .thenReturn(true)

        onView(withId(R.id.title)).check(matches(withText(
            R.string.favorites
        )))

    }

    @Test
    fun test_isHomeFragmentDisplayed() {
        val menuItems = bottomNavigation.menu

        `when`(mockedListener.onNavigationItemSelected(menuItems.findItem(R.id.home)))
            .thenReturn(true)

        onView(withId(R.id.title)).check(matches(withEffectiveVisibility(Visibility.GONE)))

    }

    @Test
    fun test_isSettingsFragmentDisplayed() {
        val menuItems = bottomNavigation.menu

        `when`(mockedListener.onNavigationItemSelected(menuItems.findItem(R.id.settings)))
            .thenReturn(true)

        print(activityTestRule.activity.findViewById<TextView>(R.id.title).text)
        onView(withId(R.id.title)).check(matches(withText(
            R.string.settings
        )))

    }

}