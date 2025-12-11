package ru.cyberprot.saper2025.dto

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Weapon(
    val id: Int,
    val name: String,
    val images: List<String>,
    val description: String
) : Parcelable

data class WeaponListWrapper(
    val ppMines: List<Weapon>? = null,
    val ptMines: List<Weapon>? = null,
    val pzrk: List<Weapon>? = null,
    val ptrk: List<Weapon>? = null,
    val otrk: List<Weapon>? = null,
    val rszo: List<Weapon>? = null



)