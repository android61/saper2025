// WeaponAdapter.kt
package ru.cyberprot.saper2025.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import ru.cyberprot.saper2025.R
import ru.cyberprot.saper2025.dto.Weapon

class WeaponAdapter : ListAdapter<Weapon, WeaponAdapter.WeaponViewHolder>(DiffCallback()) {

    private val expandedPositions = mutableSetOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder =
        WeaponViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_weapon, parent, false),
            ::toggleExpanded
        )

    override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {
        holder.bind(getItem(position), expandedPositions.contains(position))
    }

    fun toggleExpanded(position: Int) {
        if (expandedPositions.contains(position))
            expandedPositions.remove(position)
        else
            expandedPositions.add(position)
        notifyItemChanged(position)
    }

    class WeaponViewHolder(
        itemView: View,
        private val onExpandToggle: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.detail_title)
        private val imageViewPager: ViewPager2 = itemView.findViewById(R.id.image_scroll)
        private val description: TextView = itemView.findViewById(R.id.detail_description)
        private val buttonExpand: TextView = itemView.findViewById(R.id.button_expand)

        fun bind(weapon: Weapon, expanded: Boolean) {
            title.text = weapon.name
            description.text = weapon.description
            description.visibility = if (expanded) View.VISIBLE else View.GONE
            buttonExpand.text = if (expanded) "Скрыть" else "Описание"

            val context = itemView.context
            val imageResIds = weapon.images.mapNotNull { imageName ->
                context.resources.getIdentifier(imageName, "drawable", context.packageName).takeIf { it != 0 }
            }
            imageViewPager.adapter = ImageAdapter(imageResIds)

            buttonExpand.setOnClickListener {
                onExpandToggle(adapterPosition)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Weapon>() {
        override fun areItemsTheSame(old: Weapon, new: Weapon) = old.id == new.id
        override fun areContentsTheSame(old: Weapon, new: Weapon) = old == new
    }
}