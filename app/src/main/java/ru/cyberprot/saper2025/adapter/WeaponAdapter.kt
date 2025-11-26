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
    private val PAYLOAD_EXPAND_TOGGLE = "PAYLOAD_EXPAND_TOGGLE"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponViewHolder =
        WeaponViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_weapon, parent, false),
            ::toggleExpanded
        )

    override fun onBindViewHolder(holder: WeaponViewHolder, position: Int) {
        holder.bind(getItem(position), expandedPositions.contains(position))
    }

    override fun onBindViewHolder(
        holder: WeaponViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.contains(PAYLOAD_EXPAND_TOGGLE)) {
            // Если есть payload, это частичное обновление.
            // Обновляем только состояние видимости описания.
            holder.updateExpansionState(expandedPositions.contains(position))
        } else {
            // Если payload пуст, это полное обновление.
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    fun toggleExpanded(position: Int) {
        if (expandedPositions.contains(position))
            expandedPositions.remove(position)
        else
            expandedPositions.add(position)
        // Отправляем payload, чтобы указать на частичное обновление
        notifyItemChanged(position, PAYLOAD_EXPAND_TOGGLE)
    }

    class WeaponViewHolder(
        itemView: View,
        private val onExpandToggle: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.detail_title)
        private val imageViewPager: ViewPager2 = itemView.findViewById(R.id.image_scroll)
        private val description: TextView = itemView.findViewById(R.id.detail_description)
        private val buttonExpand: TextView = itemView.findViewById(R.id.button_expand)

        // --- ИЗМЕНЕНИЕ: Создаем адаптер ImageAdapter как поле ---
        private val imageAdapter = ImageAdapter(emptyList()) // Инициализируем пустым списком

        init {
            // Устанавливаем слушатель клика один раз при создании ViewHolder
            buttonExpand.setOnClickListener {
                onExpandToggle(adapterPosition)
            }
            // --- ИЗМЕНЕНИЕ: Устанавливаем ViewPager2 адаптер ОДИН РАЗ ---
            imageViewPager.adapter = imageAdapter
        }

        fun bind(weapon: Weapon, expanded: Boolean) {
            // Этот метод теперь вызывается только при полном обновлении элемента
            title.text = weapon.name
            description.text = weapon.description

            val context = itemView.context
            val imageResIds = weapon.images.mapNotNull { imageName ->
                // Используем ресурс ID
                context.resources.getIdentifier(imageName, "drawable", context.packageName).takeIf { it != 0 }
            }

            // --- ИЗМЕНЕНИЕ: Обновляем данные в существующем адаптере ---
            imageAdapter.updateImages(imageResIds)

            // Применяем начальное состояние видимости
            updateExpansionState(expanded)
        }

        fun updateExpansionState(expanded: Boolean) {
            // Этот метод отвечает только за обновление вида кнопки и описания
            description.visibility = if (expanded) View.VISIBLE else View.GONE
            buttonExpand.text = if (expanded) "Скрыть" else "Описание"
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Weapon>() {
        override fun areItemsTheSame(old: Weapon, new: Weapon) = old.id == new.id
        override fun areContentsTheSame(old: Weapon, new: Weapon) = old == new
    }
}