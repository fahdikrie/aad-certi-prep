package com.dicoding.todoapp.ui.list

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.add.AddTaskActivity
import org.junit.Before
import org.junit.Test

//DONE 16 : Write UI test to validate when user tap Add Task (+), the AddTaskActivity displayed
class HomeActivityTest {
    // Reference:
    // https://stackoverflow.com/questions/25998659/espresso-how-can-i-check-if-an-activity-is-launched-after-performing-a-certain
    @Before
    fun setUp(){
        ActivityScenario.launch(TaskActivity::class.java)
    }

    @Test
    fun assertFabToAddTaskIsWorking(){
        Intents.init()
        onView(ViewMatchers.withId(R.id.fab))
            .perform(ViewActions.click())
        intended(IntentMatchers.hasComponent(AddTaskActivity::class.java.name))
        Intents.release()
    }
}