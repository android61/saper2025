package ru.cyberprot.saper2025.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.cyberprot.saper2025.dto.Weapon
import ru.cyberprot.saper2025.repository.WeaponRepository

// Наследуемся от AndroidViewModel для доступа к контексту приложения
class WeaponListViewModel(application: Application) : AndroidViewModel(application) {
    private val _items = MutableLiveData<List<Weapon>>()
    val items: LiveData<List<Weapon>> get() = _items

    private var allWeapons: List<Weapon> = emptyList()

    fun loadWeapons(type: String) {
        if (_items.value == null) {
            // Используем getApplication() для получения контекста
            val weaponList = WeaponRepository.getMinesByType(getApplication(), type)
            allWeapons = weaponList
            _items.value = allWeapons
        }
    }

    fun filterWeapons(query: String) {
        val normalizedQuery = query.replace("-", "").replace(" ", "")

        val filteredList = if (query.isEmpty()) {
            allWeapons
        } else {
            allWeapons.filter { weapon ->
                val normalizedName = weapon.name.replace("-", "").replace(" ", "")
                normalizedName.contains(normalizedQuery, ignoreCase = true)
            }
        }
        _items.value = filteredList
    }
}