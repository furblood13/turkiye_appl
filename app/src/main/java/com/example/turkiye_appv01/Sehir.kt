package com.example.turkiye_appv01

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sehir(
    @PrimaryKey(autoGenerate = true)
    var SehirID: Int,
    @ColumnInfo(name = "SehirAD")
    var sehirad: String,
    @ColumnInfo(name = "SehirResim")
    var sehirresim: ByteArray,
    @ColumnInfo(name = "SehirAciklama")
    var sehiraciklama: String
) {


}