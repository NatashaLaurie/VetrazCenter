package com.example.vetrazcenter.presentation.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.vetrazcenter.R
import com.example.vetrazcenter.utils.Constants.ART_CREATIVITY
import com.example.vetrazcenter.utils.Constants.CHOREOGRAPHIC_CREATIVITY
import com.example.vetrazcenter.utils.Constants.CLASSES_FOR_PRESCHOOLERS
import com.example.vetrazcenter.utils.Constants.DECORATION_CREATIVITY
import com.example.vetrazcenter.utils.Constants.ECOLOGY_AND_COUNTRIES
import com.example.vetrazcenter.utils.Constants.FOREIGN_LANGUAGES
import com.example.vetrazcenter.utils.Constants.INTELLECTUAL_CREATIVITY
import com.example.vetrazcenter.utils.Constants.IT_TECHNOLOGY
import com.example.vetrazcenter.utils.Constants.MUSIC_CREATIVITY
import com.example.vetrazcenter.utils.Constants.SINGING_DIRECTION
import com.example.vetrazcenter.utils.Constants.SPORT_DIRECTION
import com.example.vetrazcenter.utils.Constants.TECHNICAL_CREATIVITY
import com.example.vetrazcenter.databinding.CategoryItemBinding

class CategoriesAdapter() :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private val categories = listOf(
        CategoryInfo(R.string.decorative_creativity, R.drawable.decorative, DECORATION_CREATIVITY),
        CategoryInfo(
            R.string.choreographic_creativity,
            R.drawable.choreography,
            CHOREOGRAPHIC_CREATIVITY
        ),
        CategoryInfo(R.string.sport_direction, R.drawable.sport, SPORT_DIRECTION),
        CategoryInfo(R.string.singing_direction, R.drawable.singing, SINGING_DIRECTION),
        CategoryInfo(R.string.art_creativity, R.drawable.art, ART_CREATIVITY),
        CategoryInfo(R.string.music_creativity, R.drawable.musik, MUSIC_CREATIVITY),
        CategoryInfo(R.string.ecology_and_countries, R.drawable.ecology, ECOLOGY_AND_COUNTRIES),
        CategoryInfo(R.string.it_technology, R.drawable.it_technology, IT_TECHNOLOGY),
        CategoryInfo(R.string.foreign_languages, R.drawable.languages, FOREIGN_LANGUAGES),
        CategoryInfo(
            R.string.intellectual_creativity,
            R.drawable.intellect,
            INTELLECTUAL_CREATIVITY
        ),
        CategoryInfo(
            R.string.classes_for_preschoolers,
            R.drawable.children,
            CLASSES_FOR_PRESCHOOLERS
        ),
        CategoryInfo(R.string.technical_creativity, R.drawable.tech_art, TECHNICAL_CREATIVITY)
    )

    inner class CategoriesViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: CategoryInfo) {
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(category)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = categories[position]
        holder.binding.apply {
            ivCategory.setImageResource(category.imageId)
            tvCategory.setText(category.categoryNameId)
        }
        holder.binding.card.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.holder_animation)
        holder.bind(category)
    }

    private var onItemClickListener: ((CategoryInfo) -> Unit)? = null

    fun setOnItemClickListener(listener: (CategoryInfo) -> Unit) {
        onItemClickListener = listener
    }
}
