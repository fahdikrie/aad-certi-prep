package com.dicoding.courseschedule.notification

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.data.Course
import com.dicoding.courseschedule.data.DataRepository
import com.dicoding.courseschedule.ui.home.HomeActivity
import com.dicoding.courseschedule.util.*
import java.util.*

class DailyReminder : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        executeThread {
            val repository = DataRepository.getInstance(context)
            val courses = repository?.getTodaySchedule()
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
            class TaskActivityTest {
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
            Log.d("DailyReminder", "Courses: ${courses?.size}")

            courses?.let {
                if (it.isNotEmpty()) showNotification(context, it)
            }
        }
    }

    //DONE 12 : Implement daily reminder for every 06.00 a.m using AlarmManager
    fun setDailyReminder(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, DailyReminder::class.java)

        val timeArray = REMINDER_TIME.split(":".toRegex())
            .dropLastWhile { it.isEmpty() }
            .toTypedArray()
        val calendar = Calendar.getInstance()
        calendar.apply {
            set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]))
            set(Calendar.MINUTE, Integer.parseInt(timeArray[0]))
            set(Calendar.SECOND, 0)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            ID_REPEATING,
            intent,
            PendingIntent.FLAG_IMMUTABLE,
        )

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )

        Log.d("DailyReminder", "Alarm enabled")
        Toast.makeText(
            context,
            "Reminder is now set at $REMINDER_TIME everyday",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun cancelAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, DailyReminder::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            ID_REPEATING,
            intent,
            PendingIntent.FLAG_IMMUTABLE,
        )
        pendingIntent.cancel()

        alarmManager.cancel(pendingIntent)

        Log.d("DailyReminder", "Alarm disabled")
        Toast.makeText(
            context,
            "Reminder is now cancelled",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun showNotification(context: Context, content: List<Course>) {
        //DONE 13 : Show today schedules in inbox style notification & open HomeActivity when notification tapped
        val notificationStyle = NotificationCompat.InboxStyle()
        val timeString = context.resources.getString(R.string.notification_message_format)
        content.forEach {
            val courseData = String.format(timeString, it.startTime, it.endTime, it.courseName)
            notificationStyle.addLine(courseData)
        }

        // Reference:
        // https://stackoverflow.com/a/69524560/13018015
        val intent = Intent(context, HomeActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setStyle(notificationStyle)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle(context.getString(R.string.today_schedule))
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setSound(alarmSound)
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)
            notification.setChannelId(NOTIFICATION_CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }

        Log.d("DailyReminder", "Show notification")
        notificationManager.notify(ID_REPEATING, notification.build())
    }

    companion object {
        private const val REMINDER_TIME = "06:00"
    }
}