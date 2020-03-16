package com.sample.gitrepos

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.sample.gitrepos.views.ReposListActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


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