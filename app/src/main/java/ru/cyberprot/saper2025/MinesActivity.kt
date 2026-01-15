package ru.cyberprot.saper2025

import android.content.Intent
import android.os.Bundle
import android.widget.Button

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import ru.cyberprot.saper2025.WeaponCategoryActivity.Companion.EXTRA_WEAPON_TYPE // Важный импорт
import ru.cyberprot.saper2025.ui.setupNavigation


class MinesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mines)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setupNavigation(toolbar)

        findViewById<Button>(R.id.button_to_PP).setOnClickListener {
            val intent = Intent(this, WeaponCategoryActivity::class.java).apply {
                // Кладем в Intent тип "противопехотные мины"
                putExtra(EXTRA_WEAPON_TYPE, "ppMines")
            }
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_to_PT).setOnClickListener {
            val intent = Intent(this, WeaponCategoryActivity::class.java).apply {
                // Кладем в Intent тип "противотанковые мины"
                putExtra(EXTRA_WEAPON_TYPE, "ptMines")
            }
            startActivity(intent)
        }

    }
}