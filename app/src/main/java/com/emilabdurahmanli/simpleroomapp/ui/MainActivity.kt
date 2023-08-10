package com.emilabdurahmanli.simpleroomapp.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.emilabdurahmanli.simpleroomapp.R
import com.emilabdurahmanli.simpleroomapp.adapter.RecyclerAdapter
import com.emilabdurahmanli.simpleroomapp.databinding.ActivityMainBinding
import com.emilabdurahmanli.simpleroomapp.model.User
import com.emilabdurahmanli.simpleroomapp.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var users : List<User>
    private lateinit var db : AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        CoroutineScope(Dispatchers.IO).launch {
             db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "User"
            ).build()
            viewModel.getAll(db.userDao())
        }
        users = listOf()
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = RecyclerAdapter(users)
        setUpClickListeners()
        setUpObservers()

    }
    fun setUpClickListeners(){
        binding.saveButton.setOnClickListener {
            if(!binding.firstNameET.text.toString().equals("") && !binding.lastNameET.text.toString().equals("")){
                val user = User(null, binding.firstNameET.text.toString(), binding.lastNameET.text.toString())
                viewModel.insertUser(db.userDao(), user)
            }else{
                Toast.makeText(this, "You entered something wrong", Toast.LENGTH_LONG).show()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setUpObservers(){
        viewModel.observeUsers().observe(this, Observer { users ->
            this.users = users
            (binding.recyclerView.adapter as RecyclerAdapter).list = users
            (binding.recyclerView.adapter as RecyclerAdapter).notifyDataSetChanged()

        })

        viewModel.observeMessage().observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        })
    }
}