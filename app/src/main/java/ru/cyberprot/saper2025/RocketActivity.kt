package ru.cyberprot.saper2025

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import ru.cyberprot.saper2025.WeaponCategoryActivity.Companion.EXTRA_WEAPON_TYPE

class RocketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rocket)

        findViewById<Button>(R.id.button_to_pzrk).setOnClickListener {
            val intent = Intent(this, WeaponCategoryActivity::class.java).apply {
                // Кладем в Intent тип "противопехотные мины"
                putExtra(EXTRA_WEAPON_TYPE, "pzrk")
            }
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_to_ptrk).setOnClickListener {
            val intent = Intent(this, WeaponCategoryActivity::class.java).apply {
                // Кладем в Intent тип "противотанковые мины"
                putExtra(EXTRA_WEAPON_TYPE, "ptrk")
            }
            startActivity(intent)
        }

    }
}