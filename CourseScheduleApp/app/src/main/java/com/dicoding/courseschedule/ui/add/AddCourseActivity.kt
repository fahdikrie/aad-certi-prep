package com.dicoding.courseschedule.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.util.TimePickerFragment

class AddCourseActivity : AppCompatActivity(), TimePickerFragment.DialogTimeListener {

    private lateinit var addCourseViewModel: AddCourseViewModel
    private lateinit var timePickerFragment: TimePickerFragment
    private lateinit var startTime: String
    private lateinit var endTime: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)
        timePickerFragment = TimePickerFragment()

        val factory = AddCourseViewModelFactory.createFactory(this)
        addCourseViewModel = ViewModelProvider(this, factory)[AddCourseViewModel::class.java]

        val startTimeIb = findViewById<ImageButton>(R.id.ib_start_time)
        startTimeIb.setOnClickListener {
            timePickerFragment.show(supportFragmentManager, "start_time")
        }

        val endTimeIb = findViewById<ImageButton>(R.id.ib_end_time)
        endTimeIb.setOnClickListener {
            timePickerFragment.show(supportFragmentManager, "end_time")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_insert -> {
                if (!this::startTime.isInitialized || !this::endTime.isInitialized) {
                    Toast.makeText(
                        this,
                        getString(R.string.time_empty_toast_message),
                        Toast.LENGTH_SHORT
                    ).show()
                    return false
                }

                val courseName = findViewById<EditText>(R.id.ed_course_name).text.toString()
                val day = findViewById<Spinner>(R.id.spinner_day).selectedItemPosition
                val lecturer = findViewById<EditText>(R.id.ed_lecturer).text.toString()
                val note = findViewById<EditText>(R.id.ed_note).text.toString()

                addCourseViewModel.insertCourse(
                    courseName,
                    day,
                    startTime,
                    endTime,
                    lecturer,
                    note,
                )

                addCourseViewModel.saved.observe(this) { event ->
                    event.getContentIfNotHandled().let { isSaved ->
                        if (isSaved == true) {
                            Toast.makeText(
                                this,
                                "Course successfully added!",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                "Oops! Error when adding course",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDialogTimeSet(tag: String?, hour: Int, minute: Int) {
        val time = "${hour}:${minute}"
        val startTimeClock = findViewById<TextView>(R.id.tv_start_time_clock)
        val endTimeClock = findViewById<TextView>(R.id.tv_end_time_clock)

        when (tag) {
            START_TIME -> {
                startTime = time
                startTimeClock.text = time
            }
            END_TIME -> {
                endTime = time
                endTimeClock.text = time
            }
            else -> {}
        }
    }

    companion object {
        private const val START_TIME = "start_time"
        private const val END_TIME = "end_time"
    }
}