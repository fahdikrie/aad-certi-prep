Hello, my name is Fahdii, i'm from Indonesia... and this is my take for the exam #2 exit interview on my implementation of the Course Schedule App

1. What project have you worked about?

Course Schedule App is a simple android application that helps user to manage their schedules. There are many functionalities inside this app, in which all of them are:
- Get to know the nearest schedule
- Look at all of the schedules available
- Filter schedules based on the time, the course name, and the lecturer, alphabetically
- See the detail information of a schedule
- Add a new schedule
- Delete a schedule, both by swiping and by clicking a button, and
- Settings page to set the theme and notification based on your preferences.

Looking from the UI perspective, there are five screens available on this app, which are:
- Home Screen
- Task List Screen
- Task Detail Screen
- Add Task Screen
- Settings Screen

There is also an important feature that needs to be mentioned. The Course Schedule App also have a notification system that will tell the user all of the schedules respective to each day. The notification will pop everyday at 6 AM, and the best thing is that you can turn off the notification suppose you don't see it as necessary.

Lastly, the technologies used in this app are:
- Kotlin as the programming language
- RecyclerView
- ViewModel & LiveData
- PagedList & RawQuery Filtering
- Scheduler & NotificationManager, and
- Room & Room Prepopulate database

Onto the next question,
2. Which part is hardest?

I think, for this app, the hardest part for me must've been when I tried to implement the reminder alarm. I spent probably like 5 hours alone trying to figure out why the notification won't pop out, even though i'm already using setRepeating alarm.

After carefully reinspecting an exercise that I once did from Dicoding, and stroll around the stackoverflow, the problem arouse from the type of PendingIntent that i'm using. It appears that there is a compatibility issue when I use the same PendingIntent as displayed in the Dicoding example.

But from trying to troubleshoot this problem alone, this is definitely a huge leap for me in terms of getting familiar with alarm & notification in android.

For the third question,
3. How is the flow to change the theme to a dark theme?

There are multiple ways you can implement a light and dark theme switch in an app. For instance, you can use datastore to store the theme preferences, you can also go for the petty approach by creating an intent to theme settings. But for this app, we store the simply implement a UI with a switch button, and in the background, we handle the changes of the switch by changing the preference of our theme.

Onto the fourth question,
4. How does notification reminder work?

In contrast to the Todo app from the previous exam, the reminder for the course schedule in this app is implemented using a BroadcastReceiver.

To put it simply, BroadcastReceiver basically listens to a set of events or requests. And when an event happen, or a request is sent, the BroadcastReceiver will trigger one or a set of defined operations.

In this case, we create a "request" to a BroadcastReceiver using PendingIntent's method of getBroadcast. By calling that method, it will trigger a set of operations defined on the onReceiver method. For our case, the onReceiver method is implemented by calling the NotificationManager, and showing the nearest courses in respect to the current time.

And for the last question,
5. Why do we need LiveData?

In Android there is a concept called lifecycle. An activity or a fragment each has its own lifecycle, from the moment they are created, loaded with data, up until the moment they are destroyed.

The LiveData class allows developer to observe to a data only to a specific lifecycles where that data will be needed. In short, LiveData's purpose is to keep the data up-to-date while respecting the Activity/Fragment lifecycle, meaning that it will be maintained even though the Activity/the Fragment is undergoing a change of lifecycle.
