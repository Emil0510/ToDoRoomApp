package com.emilabdurahmanli.simpleroomapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emilabdurahmanli.simpleroomapp.databinding.RecyclerRowBinding
import com.emilabdurahmanli.simpleroomapp.model.User

class RecyclerAdapter(var list : List<User>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(var binding : RecyclerRowBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.firstNameText.text = list[position].firstName
        holder.binding.lastNameText.text = list[position].lastName
    }

}