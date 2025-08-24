// WeaponCategoryActivity.kt
package ru.cyberprot.saper2025

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.cyberprot.saper2025.ui.WeaponListFragment
import androidx.appcompat.widget.SearchView

class WeaponCategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weapon_category)

        if (savedInstanceState == null) {
            // Получаем тип оружия из Intent, который был передан при запуске
            val weaponType = intent.getStringExtra(EXTRA_WEAPON_TYPE)
                ?: throw IllegalArgumentException("Weapon type must be provided")

            // Создаем фрагмент с нужным типом
            val fragment = WeaponListFragment.newInstance(weaponType)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
        val searchView = findViewById<androidx.appcompat.widget.SearchView>(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Ничего не делаем при отправке
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as? WeaponListFragment
                fragment?.filterList(newText.orEmpty())
                return true
            }
        })
    }

    companion object {
        // Ключ для передачи данных через Intent
        const val EXTRA_WEAPON_TYPE = "weapon_type"
    }
}