package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID

class DetailTaskActivity : AppCompatActivity() {

    private var taskID: Int = -1
    private lateinit var detailTaskViewModel: DetailTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //DONE 11 : Show detail task and implement delete action
        taskID = intent.getIntExtra(TASK_ID, -1)
        if (taskID == -1) finish()

        val factory = ViewModelFactory.getInstance(this)
        detailTaskViewModel = ViewModelProvider(this, factory)[DetailTaskViewModel::class.java]
        detailTaskViewModel.setTaskId(taskID)

        val titleTv = findViewById<TextView>(R.id.detail_ed_title)
        val descriptionTv = findViewById<TextView>(R.id.detail_ed_description)
        val dateTv = findViewById<TextView>(R.id.detail_ed_due_date)

        detailTaskViewModel.task.observe(this) {
            titleTv.text = it?.title
            descriptionTv.text = it?.description
            dateTv.text = it?.dueDateMillis?.let { dueDate -> DateConverter.convertMillisToString(dueDate) }
        }

        val deleteBtn = findViewById<Button>(R.id.btn_delete_task)
        deleteBtn.setOnClickListener {
            detailTaskViewModel.deleteTask()
            Toast.makeText(
                this,
                "Task successfully deleted!",
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
    }
}