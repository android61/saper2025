package ru.cyberprot.saper2025.viewmodel


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.cyberprot.saper2025.dto.Weapon
import ru.cyberprot.saper2025.repository.WeaponRepository

class WeaponListViewModel : ViewModel() {
    private val _items = MutableLiveData<List<Weapon>>()
    val items: LiveData<List<Weapon>> get() = _items

    private var allWeapons: List<Weapon> = emptyList()

    fun loadWeapons(context: Context, type: String) {
        if (_items.value == null) {
            val weaponList = WeaponRepository.getMinesByType(context, type)
            allWeapons = weaponList
            _items.value = allWeapons
        }
    }

    fun filterWeapons(query: String) {
        // Убираем дефисы и пробелы из поискового запроса
        val normalizedQuery = query.replace("-", "").replace(" ", "")

        val filteredList = if (query.isEmpty()) {
            allWeapons
        } else {
            allWeapons.filter { weapon ->
                // Убираем дефисы и пробелы из названия оружия для сравнения
                val normalizedName = weapon.name.replace("-", "").replace(" ", "")
                normalizedName.contains(normalizedQuery, ignoreCase = true)
            }
        }
        _items.value = filteredList
    }
}