package com.example.sinau.model

class User(var name:String, var password:String) {
    var courses = mutableListOf<Course>()
    var myCourses = mutableListOf<Course>()
}