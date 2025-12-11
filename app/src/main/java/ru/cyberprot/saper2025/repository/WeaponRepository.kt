package ru.cyberprot.saper2025.repository

import android.content.Context
import com.google.gson.Gson
import ru.cyberprot.saper2025.dto.Weapon
import ru.cyberprot.saper2025.dto.WeaponListWrapper

object WeaponRepository {
    fun getMinesByType(context: Context, type: String): List<Weapon> {
        val fileName = "datasample/${type}.json"
        val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        val wrapper = Gson().fromJson(jsonString, WeaponListWrapper::class.java)
        return when (type) {
            "ppMines" -> wrapper.ppMines ?: emptyList()
            "ptMines" -> wrapper.ptMines ?: emptyList()
            "pzrk" -> wrapper.pzrk ?: emptyList()
            "ptrk" -> wrapper.ptrk ?: emptyList()
            "otrk" -> wrapper.otrk ?: emptyList()
            "rszo" -> wrapper.rszo ?: emptyList()
            else -> emptyList()
        }
    }
}