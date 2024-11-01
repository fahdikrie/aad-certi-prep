package com.dicoding.courseschedule.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.data.Course
import com.dicoding.courseschedule.ui.add.AddCourseActivity
import com.dicoding.courseschedule.ui.list.ListActivity
import com.dicoding.courseschedule.ui.setting.SettingsActivity
import com.dicoding.courseschedule.util.DayName
import com.dicoding.courseschedule.util.QueryType
import com.dicoding.courseschedule.util.timeDifference

//DONE 15 : Write UI test to validate when user tap Add Course (+) Menu, the AddCourseActivity is displayed
class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel
    private var queryType = QueryType.CURRENT_DAY

    //DONE 5 : Show today schedule in CardHomeView and implement menu action
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.title = resources.getString(R.string.today_schedule)

        // Creating new class of HomeViewModelFactory
        // to maintain consistency with other views
        val factory = HomeViewModelFactory.createFactory(this)
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        viewModel.getNearestSchedule(queryType).observe(this) {
            showTodaySchedule(it)
        }
    }

    private fun showTodaySchedule(course: Course?) {
        val cardHome = findViewById<CardHomeView>(R.id.view_home)
        val emptyHomeTv = findViewById<TextView>(R.id.tv_empty_home)

        if (course == null) {
            cardHome.visibility = View.GONE
            emptyHomeTv.visibility = View.VISIBLE
            return
        } else {
            cardHome.visibility = View.VISIBLE
            emptyHomeTv.visibility = View.GONE
        }

        checkQueryType(course)
        course.apply {
            val dayName = DayName.getByNumber(day)
            val time = String.format(getString(R.string.time_format), dayName, startTime, endTime)
            var remainingTime = timeDifference(day, startTime)

            // A very hacky and extremely poor modification
            // tapi takut malah kena masalah kalau ubah-ubah
            // boilerplate code query, jadi diimplementasikan di sini
            if (remainingTime == "(In 7 days)") remainingTime = "(Today)"

            cardHome.apply {
                setCourseName(course.courseName)
                setTime(time)
                setLecturer(course.lecturer)
                setNote(course.note)
                setRemainingTime(remainingTime)
            }
        }
    }

    private fun checkQueryType(course: Course?) {
        if (course == null) {
            val newQueryType: QueryType = when (queryType) {
                QueryType.CURRENT_DAY -> QueryType.NEXT_DAY
                QueryType.NEXT_DAY -> QueryType.PAST_DAY
                else -> QueryType.CURRENT_DAY
            }
            viewModel.setQueryType(newQueryType)
            queryType = newQueryType
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent: Intent = when (item.itemId) {
            R.id.action_add -> Intent(this, AddCourseActivity::class.java)
            R.id.action_list -> Intent(this, ListActivity::class.java)
            R.id.action_settings -> Intent(this, SettingsActivity::class.java)
            else -> null
        } ?: return super.onOptionsItemSelected(item)

        startActivity(intent)
        return true
    }
}