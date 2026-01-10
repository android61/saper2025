package ru.cyberprot.saper2025.repository

import android.content.Context
import com.google.gson.Gson
import ru.cyberprot.saper2025.dto.Weapon
import ru.cyberprot.saper2025.dto.WeaponListWrapper

object WeaponRepository {
    fun getMinesByType(context: Context, type: String): List<Weapon> {
        val fileName = "datasample/${type}.json"

        // Метод .use гарантирует закрытие потока (предотвращает утечку close)
        val jsonString = try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            return emptyList()
        }

        val wrapper = Gson().fromJson(jsonString, WeaponListWrapper::class.java)

        return when (type) {
            "ppMines"        -> wrapper.ppMines
            "ptMines"        -> wrapper.ptMines
            "pzrk"           -> wrapper.pzrk
            "ptrk"           -> wrapper.ptrk
            "otrk"           -> wrapper.otrk
            "rszo"           -> wrapper.rszo
            "handgrenade"    -> wrapper.handgrenade
            "handlauncher"   -> wrapper.handlauncher
            else             -> emptyList()
        } ?: emptyList() // Если в JSON поле null, возвращаем пустой список
    }
}