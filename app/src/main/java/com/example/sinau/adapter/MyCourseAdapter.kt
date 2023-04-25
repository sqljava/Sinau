package com.example.sinau.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sinau.databinding.MyCourseItemBinding
import com.example.sinau.model.Course

class MyCourseAdapter(var list: MutableList<Course>) :
    RecyclerView.Adapter<MyCourseAdapter.MyHolder>() {

    class MyHolder(var binding: MyCourseItemBinding):
        RecyclerView.ViewHolder(binding.root){
        var name = binding.myCourseName
        var img = binding.myCourseImg
        var duration = binding.myCourseDuration

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(MyCourseItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.name.text = list[position].name
        holder.duration.text = list[position].duration.toString()+" lessons"
        holder.img.setImageResource(list[position].img)
    }
}