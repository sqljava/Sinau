package com.example.sinau.model

class Course(var name : String, var price : Double,var img:Int) {
    var lessons = mutableListOf<Lesson>()
    var duration = lessons.size
    var liked = false
    var rate = 0
}