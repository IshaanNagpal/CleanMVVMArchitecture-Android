package com.sample.gitrepos

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.sample.gitrepos.utility.GenericViewHolder
import com.sample.gitrepos.utility.ListItemModel
import com.sample.gitrepos.views.ReposListActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class RepoListTestActivity {

    @get: Rule
    val activityTestRule = ActivityTestRule<ReposListActivity>(ReposListActivity::class.java, true, false)


    @Before
    fun beforeEach() {
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun checkIfShimmerIsShown() {
        onView(withId(R.id.shimmer_view_container)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun checkIfRecyclerViewIsShown() {
        onView(withId(R.id.repos_recyclerview)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun checkIfErrorScreenIsNotShownByDefault() {
        onView(withId(R.id.error_layout)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun checkForTheClickOnRecyclerview() {
//        onView(withId(R.id.repos_recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
//        onView(withId(R.id.info_container_layout)).check(matches(isDisplayed()))
    }
}