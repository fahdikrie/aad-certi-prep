Hello, my name is Fahdii, i'm from Indonesia... and this is my take for the exam #3 exit interview on my implementation of the Habit Tracker App

1. What project have you worked about?

Habit Tracker App is a simple app to help user to track track and maintain their habit. It has many interesting and useful features, such as

2. Which part is hardest?

As for this app, I don't really feel any trouble due to its similarity to the two previous two apps.

Actually there is one part that I feel a little troubled with. I was a little bit confused, but then I remember about the OneTimeWorkRequest exercise from Dicoding, and I finally got a little grasp about it after a little revisit.

3. How is the flow of showing random habit using ViewPager2?

On this app, the random habit is arranged using TabLayout and Fragments. The ViewPager is there to make the tab swipable to the left and right. The implementation is similar to implementing a RecyclerView, there are adapter, and pager_item.

4. How does notification reminder work?

It's basically the same answer to the first exam, since both of this app and the Todo app are using the similar notification logics.

The idea of how a notification work is that we basically registering for a "Scheduler" through our app in the background.
The scheduler is "booked" by a WorkManager, there are multiple types of WorkManager task, in this case we are using a PeriodicWorker, so that the reminder will be repeated with our preferred interval time.

5. Why do we need a ViewModel?

On writing an android app, there are a handful of approaches on how should we structure and layer the architecture of our code. One of the approach is called MVVM or Model View and ViewModel architecture.

Knowing that information, basically the idea of ViewModel is to give a separation of the business logic on the Model from the presentational logic on the View. This allows us developers to write a cleaner and more structured android project.
