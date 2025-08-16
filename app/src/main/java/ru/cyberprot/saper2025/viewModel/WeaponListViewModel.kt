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

    // Метод для загрузки данных
    fun loadWeapons(context: Context, type: String) {
        // Загружаем только если данных еще нет
        if (_items.value == null) {
            val weaponList = WeaponRepository.getMinesByType(context, type)
            _items.value = weaponList
        }
    }
}