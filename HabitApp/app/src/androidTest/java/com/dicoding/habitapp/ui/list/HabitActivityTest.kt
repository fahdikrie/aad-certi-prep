package com.dicoding.habitapp.ui.list

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import com.dicoding.habitapp.R
import com.dicoding.habitapp.ui.add.AddHabitActivity
import org.junit.Before
import org.junit.Test


//DONE 16 : Write UI test to validate when user tap Add Habit (+), the AddHabitActivity displayed
class HabitActivityTest {
    // Reference:
    // https://stackoverflow.com/questions/25998659/espresso-how-can-i-check-if-an-activity-is-launched-after-performing-a-certain
    @Before
    fun setUp(){
        ActivityScenario.launch(HabitListActivity::class.java)
    }

    @Test
    fun assertFabToAddHabitIsWorking(){
        Intents.init()
        onView(ViewMatchers.withId(R.id.fab))
            .perform(ViewActions.click())
        intended(IntentMatchers.hasComponent(AddHabitActivity::class.java.name))
        Intents.release()
    }
}