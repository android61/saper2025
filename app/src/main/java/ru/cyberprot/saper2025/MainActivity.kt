package ru.cyberprot.saper2025

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_to_mines).setOnClickListener {
            val intent = Intent(this, MinesActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.button_to_RC).setOnClickListener {
            val intent = Intent(this, RocketActivity::class.java)
            startActivity(intent)
        }

    }
}