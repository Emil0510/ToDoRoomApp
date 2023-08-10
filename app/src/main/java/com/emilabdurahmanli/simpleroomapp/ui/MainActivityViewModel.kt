package com.emilabdurahmanli.simpleroomapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.emilabdurahmanli.simpleroomapp.model.User
import com.emilabdurahmanli.simpleroomapp.room.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private var users = MutableLiveData<List<User>>()
    private var message = MutableLiveData<String>()

    fun getAll(userDao: UserDao){
        CoroutineScope(Dispatchers.Default).launch{
            val result = userDao.getAll()
            users.postValue(result)

        }
    }

    fun insertUser(userDao: UserDao, user : User){
        CoroutineScope(Dispatchers.Default).launch {
            userDao.insertUser(user)
            message.postValue("Successfully added")
            getAll(userDao)
        }
    }

    fun observeUsers():LiveData<List<User>>{
        return users
    }
    fun observeMessage():LiveData<String>{
        return message
    }
}