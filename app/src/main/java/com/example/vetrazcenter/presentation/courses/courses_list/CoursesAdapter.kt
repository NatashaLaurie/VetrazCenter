package com.example.vetrazcenter.presentation.courses.courses_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vetrazcenter.R
import com.example.vetrazcenter.data.model.courses.Course
import com.example.vetrazcenter.databinding.CourseItemBinding

class CoursesAdapter :
    RecyclerView.Adapter<CoursesAdapter.CourseViewHolder>() {

    inner class CourseViewHolder(val binding: CourseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(course: Course) {
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(course)
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Course>() {

        override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem.courseName == newItem.courseName
        }

        override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        return CourseViewHolder(
            CourseItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = differ.currentList[position]
        holder.binding.apply {
            Glide.with(this.root).load(course.imageUrl).into(ivCourseImage)
            tvTitle.text = course.courseName
            tvAge.text = holder.itemView.context.getString(
                R.string.age,
                course.studentsAge?.from.toString(),
                course.studentsAge?.to.toString()
            )
            tvPaymentTerm.text = course.paymentTerm
        }
        holder.bind(course)
    }

    private var onItemClickListener: ((Course) -> Unit)? = null

    fun setOnItemClickListener(listener: (Course) -> Unit) {
        onItemClickListener = listener
    }
}
