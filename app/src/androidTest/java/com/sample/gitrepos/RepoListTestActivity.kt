package com.sample.gitrepos

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import androidx.test.rule.ActivityTestRule
import com.sample.gitrepos.views.ReposListActivity

import androidx.test.espresso.Espresso.onView
import android.content.Intent
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test


@RunWith(AndroidJUnit4::class)
@LargeTest
class RepoListTestActivity {

    @get: Rule
    val activityTestRule = ActivityTestRule<ReposListActivity>(ReposListActivity::class.java, true, false)


    @Test
    fun checkIfShimmerIsShown() {
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.shimmer_view_container)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun checkIfRecyclerViewIsShown() {
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.shimmer_view_container)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }


    @Test
    fun checkIfErrorScreenIsNotShownByDefault() {
        activityTestRule.launchActivity(Intent())
        onView(withId(R.id.error_layout)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }
}