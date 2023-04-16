package com.example.sinau.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sinau.databinding.CourseItemBinding
import com.example.sinau.model.Course

class CourseAdapter(var list: List<Course>) : RecyclerView.Adapter<CourseAdapter.CourseHolder>() {
    class CourseHolder(var binding: CourseItemBinding) : RecyclerView.ViewHolder(binding.root){
        var name = binding.courseName
        var price = binding.coursePrice
        var img = binding.courseImg
        var rate = binding.courseRate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseHolder {
        return CourseHolder(CourseItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CourseHolder, position: Int) {
        holder.name.text = list[position].name
        holder.price.text = "$"+list[position].price.toString()
        holder.img.setImageResource(list[position].img)
        holder.rate.text = list[position].rate.toString()
    }
}