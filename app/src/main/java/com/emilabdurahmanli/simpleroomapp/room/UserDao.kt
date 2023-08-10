package com.emilabdurahmanli.simpleroomapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emilabdurahmanli.simpleroomapp.model.User

@Dao
interface UserDao {

    @Query("Select * from user")
    fun getAll() : List<User>

    @Insert
    fun insertUser(user: User)

}