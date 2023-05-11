package com.example.vetrazcenter.presentation.courses.course

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vetrazcenter.R
import com.example.vetrazcenter.data.model.local.courses.Schedule
import com.example.vetrazcenter.databinding.ScheduleItemBinding

class ScheduleAdapter : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

    private val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Schedule>() {

        override fun areItemsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
            return oldItem.groupName == newItem.groupName
        }

        override fun areContentsTheSame(oldItem: Schedule, newItem: Schedule): Boolean {
            return oldItem == newItem
        }
    })

    fun submitList(list: List<Schedule>) {
        differ.submitList(list)
    }

    inner class ScheduleViewHolder(private val binding: ScheduleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(schedule: Schedule) {
            binding.tvGroupName.text = schedule.groupName
            addTextViews(schedule.week?.mondayLessons, binding.mondayTimeLayout, binding.cvMonday)
            addTextViews(
                schedule.week?.tuesdayLessons,
                binding.tuesdayTimeLayout,
                binding.cvTuesday
            )
            addTextViews(
                schedule.week?.wednesdayLessons,
                binding.wednesdayTimeLayout,
                binding.cvWednesday
            )
            addTextViews(
                schedule.week?.thursdayLessons,
                binding.thursdayTimeLayout,
                binding.cvThursday
            )
            addTextViews(schedule.week?.fridayLessons, binding.fridayTimeLayout, binding.cvFriday)
            addTextViews(
                schedule.week?.saturdayLessons,
                binding.saturdayTimeLayout,
                binding.cvSaturday
            )
            addTextViews(schedule.week?.sundayLessons, binding.sundayTimeLayout, binding.cvSunday)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding = ScheduleItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val schedule = differ.currentList[position]

        holder.bind(schedule)
    }

    override fun getItemCount(): Int = differ.currentList.size

    private fun addTextViews(
        lessons: List<String>?,
        linearLayout: LinearLayout,
        cardView: CardView
    ) {
        if (lessons != null) {
            lessons.forEach {
                val textView = TextView(linearLayout.context)
                textView.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                textView.gravity = Gravity.CENTER
                textView.setTextColor(
                    ContextCompat.getColor(
                        linearLayout.context,
                        R.color.on_secondary
                    )
                )
                textView.text = it
                linearLayout.addView(textView)
            }
            cardView.setCardBackgroundColor(
                ContextCompat.getColor(
                    linearLayout.context,
                    R.color.primary_variant
                )
            )
        }
    }

}