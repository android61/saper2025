package ru.cyberprot.saper2025.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.cyberprot.saper2025.R

// Изменяем 'private val' на 'private var'
class ImageAdapter(private var images: List<Int>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image_view_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.imageView.setImageResource(images[position])
    }

    override fun getItemCount() = images.size

    // --- НОВЫЙ МЕТОД ДЛЯ ОБНОВЛЕНИЯ ДАННЫХ ---
    fun updateImages(newImages: List<Int>) {
        // Обновляем список изображений
        images = newImages
        // Уведомляем ViewPager2 об изменении данных, чтобы он перерисовал элементы
        notifyDataSetChanged()
    }
}