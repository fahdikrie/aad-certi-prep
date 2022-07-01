package com.dicoding.courseschedule.ui.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.ui.add.AddCourseActivity
import org.junit.Before
import org.junit.Test

class HomeActivityTest {
    // Reference:
    // https://stackoverflow.com/questions/25998659/espresso-how-can-i-check-if-an-activity-is-launched-after-performing-a-certain
    @Before
    fun setUp(){
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun assertActionMenuToAddTaskIsWorking(){
        Intents.init()
        onView(ViewMatchers.withId(R.id.action_add))
            .perform(ViewActions.click())
        intended(IntentMatchers.hasComponent(AddCourseActivity::class.java.name))
        Intents.release()
    }
}