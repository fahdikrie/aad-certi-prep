package com.dicoding.habitapp.ui.random

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.habitapp.R
import com.dicoding.habitapp.data.Habit

class RandomHabitAdapter(
    private val onClick: (Habit) -> Unit
) : RecyclerView.Adapter<RandomHabitAdapter.PagerViewHolder>() {

    private val habitMap = LinkedHashMap<PageType, Habit>()

    fun submitData(key: PageType, habit: Habit) {
        habitMap[key] = habit
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PagerViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.pager_item, parent, false))

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val key = getIndexKey(position) ?: return
        val pageData = habitMap[key] ?: return
        holder.bind(key, pageData)
    }

    override fun getItemCount() = habitMap.size

    private fun getIndexKey(position: Int) = habitMap.keys.toTypedArray().getOrNull(position)

    enum class PageType {
        HIGH, MEDIUM, LOW
    }

    inner class PagerViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        //DONE 14 : Create view and bind data to item view

        fun bind(pageType: PageType, pageData: Habit) {
            val titleTv = itemView.findViewById<TextView>(R.id.pager_tv_title)
            val startTv = itemView.findViewById<TextView>(R.id.pager_tv_start_time)
            val minutesFocusTv = itemView.findViewById<TextView>(R.id.pager_tv_minutes)
            val priorityLevelIv = itemView.findViewById<ImageView>(R.id.item_priority_level)
            val openCountdownBtn = itemView.findViewById<Button>(R.id.btn_open_count_down)

            titleTv.text = pageData.title
            startTv.text = pageData.startTime
            minutesFocusTv.text = pageData.minutesFocus.toString()

            when (pageType) {
                PageType.LOW -> priorityLevelIv.setImageResource(R.drawable.ic_priority_low)
                PageType.MEDIUM -> priorityLevelIv.setImageResource(R.drawable.ic_priority_medium)
                PageType.HIGH -> priorityLevelIv.setImageResource(R.drawable.ic_priority_high)
            }

            openCountdownBtn.setOnClickListener { onClick(pageData) }
        }
    }
}
