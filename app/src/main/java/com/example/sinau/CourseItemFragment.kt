package com.example.sinau

import android.content.Context
import android.content.SharedPreferences
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sinau.databinding.FragmentCourseItemBinding
import com.example.sinau.model.Course
import com.example.sinau.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

class CourseItemFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var param1: Course

    lateinit var binding: FragmentCourseItemBinding
    lateinit var file: SharedPreferences
    var userType = object : TypeToken<User>(){}.type
    lateinit var currentUser : User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Course
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCourseItemBinding.inflate(inflater, container, false)

        file = requireActivity().getSharedPreferences("FILE", Context.MODE_PRIVATE)
        var gson = Gson()
        val editor = file.edit()
        var strUser = file.getString("USER", "")
        currentUser  = gson.fromJson(strUser,userType)

        var course = arguments?.getSerializable(ARG_PARAM1) as Course
        var name = course.name

        binding.courseName.text = name
        binding.courseImg.setImageResource(course.img)

        binding.enrollButton.setOnClickListener {
            currentUser.myCourses.add(course)
            strUser = gson.toJson(currentUser)
            editor.putString("USER", strUser)
            editor.apply()
            parentFragmentManager.beginTransaction().replace(R.id.main,
                CongratsFragment()).commit()
        }

        binding.back.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, ParentFragment()).commit()
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Course) =
            CourseItemFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}