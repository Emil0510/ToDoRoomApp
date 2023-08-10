package com.emilabdurahmanli.simpleroomapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.emilabdurahmanli.simpleroomapp.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}