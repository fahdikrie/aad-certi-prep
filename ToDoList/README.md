Hello, my name is Fahdii, i'm from Indonesia... and this is my take for the exam #1 exit interview

For the first question,
1. What project have you worked about?

This is basically a very simple to-do app with CREATE, READ, and DELETE functionality, with its main goal is to help
the user to track their tasks.

If we look further into the app, there are four screens available inside it, which are:
- Task List Screen
- Create Task Screen
- Detail Task Screen, and
- Settings Screen

In detail screen, the user could delete their task. But for your information, this current iteration of the app
does not include Update Task functionality.

Another thing i'd like to mention about this app is that there is a notification function as a reminder. Which,
in my opinion is a very cool and fundamental thing to have in a Task Journaling App.

Lastly, the technologies used in this app are:
- Kotlin as the programming language
- CustomView
- RecyclerView
- ViewModel & LiveData
- PagedList & RawQuery Filtering
- Scheduler & NotificationManager, and
- Room & Room Prepopulate database

Onto the next question,
2. Which part is hardest?

For me, I think the hardest part or the part that took the longest of my time is to implement the notification.

The part that troubled me the most was the pendingIntent, I wasn't sure what it was about at first, but then
I looked up the documentation and Dicoding tutorial, and I started to get a grip about it.

I think this is due to my lack of practice specifically for this subject, and something I will surely look to improve
in the near future.

Other than that, I think I did pretty okay in this exam...

Alright, for the third question,
3. What components have been used to show a list of tasks?

The components used is RecyclerView. In short, RecyclerView is one of the Views in android
that allows a dynamic lists UI, in which you can dynamically manipulate the contents of that list.

Implementing recycler view is fairly easy, the main ingredients are:
- the LayoutManager
- layout item resource, and
- the adapter

Onto the fourth question,
4. How does notification reminder work?

Specifically for this app, we built a notification system with the help of Android's own NotificationManager.

The idea of how a notification work is that we basically registering for a "Scheduler" through our app in the background.
The scheduler is "booked" by a WorkManager, there are multiple types of WorkManager task, in this case we are using a PeriodicWorker, so that the reminder will be repeated with our preferred interval time.

And for the last question,
5. Why do we need a Custom View?

In this app, the custom view is used in the task item. In which it has a checkbox functionality.

Implementing a custom view allows developer to create components outside the built in android material/appcompat components to their will. Thats why Custom View is necessary for our case to create an item inside a list that has a checkbox functionality.