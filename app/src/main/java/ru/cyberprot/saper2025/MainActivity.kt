package ru.cyberprot.saper2025

import android.content.Intent
import android.os.Bundle
import android.widget.Button

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import ru.cyberprot.saper2025.ui.setupNavigation


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setupNavigation(toolbar)

        findViewById<Button>(R.id.button_to_mines).setOnClickListener {
            val intent = Intent(this, MinesActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.button_to_RC).setOnClickListener {
            val intent = Intent(this, RocketActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_to_infantry).setOnClickListener {
            val intent = Intent(this, InfantryActivity::class.java)
            startActivity(intent)
        }

        // Внутри onCreate в MainActivity.kt
        findViewById<Button>(R.id.button_to_calculate).setOnClickListener {
            val intent = Intent(this, CalculateActivity::class.java)
            startActivity(intent)
        }
    }
}