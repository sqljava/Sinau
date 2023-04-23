package com.example.sinau.adapter

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sinau.R
import com.example.sinau.databinding.CourseItemBinding
import com.example.sinau.model.Course
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CourseAdapter(var list: List<Course>, var activity:Activity) :
    RecyclerView.Adapter<CourseAdapter.CourseHolder>() {

    lateinit var file: SharedPreferences
    var courseListType = object : TypeToken<List<Course>>(){}.type
    class CourseHolder(var binding: CourseItemBinding) : RecyclerView.ViewHolder(binding.root){
        var name = binding.courseName
        var price = binding.coursePrice
        var img = binding.courseImg
        var rate = binding.courseRate
        var like = binding.courseLiked
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

        if (list[position].liked){
            holder.like.setImageResource(R.drawable.favorite)
        }else{
            holder.like.setImageResource(R.drawable.favorite_border)
        }

        holder.like.setOnClickListener {
            if(list[position].liked){
                list[position].liked=false
                holder.like.setImageResource(R.drawable.favorite_border)
            }else{
                list[position].liked = true
                holder.like.setImageResource(R.drawable.favorite)
            }
            file = activity.getSharedPreferences("FILE", Context.MODE_PRIVATE)
            var gson = Gson()
            val editor = file.edit()

            var strCourses = gson.toJson(list)
            editor.putString("COURSES", strCourses)
            editor.commit()
        }

    }
}