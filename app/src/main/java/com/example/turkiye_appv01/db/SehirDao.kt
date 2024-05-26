package com.example.turkiye_appv01.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.turkiye_appv01.Sehir

@Dao
interface SehirDao {
    @Query("Select * from Sehir")
    fun getAll() : List<Sehir>

    @Query("Select * from Sehir where SehirID = :id")
    fun selectid(id: Int) :Sehir?

    @Insert
    fun insertall(vararg sehir: Sehir)
}