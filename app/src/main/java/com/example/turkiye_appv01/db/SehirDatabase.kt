package com.example.turkiye_appv01.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.turkiye_appv01.Sehir

@Database(entities = [Sehir::class], version = 1)
abstract class SehirDatabase: RoomDatabase() {
    abstract val sehirdao: SehirDao

    companion object {
        @Volatile private var INSTANCE: SehirDatabase? = null

        fun getInstance(context: Context): SehirDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SehirDatabase::class.java,
                    "turkiyemdb.db"
                ).createFromAsset("turkiyemdb.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}