package com.example.sinau

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sinau.adapter.CourseAdapter
import com.example.sinau.databinding.FragmentCourseItemBinding
import com.example.sinau.databinding.FragmentHomeBinding
import com.example.sinau.model.Course
import com.example.sinau.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentHomeBinding
    var courseList = mutableListOf<Course>()

    lateinit var file: SharedPreferences
    var courseListType = object : TypeToken<List<Course>>(){}.type
    var userType = object : TypeToken<User>(){}.type
    var typeUsers = object : TypeToken<List<User>>(){}.type
    lateinit var currentUser : User
    var users = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        file = requireActivity().getSharedPreferences("FILE", Context.MODE_PRIVATE)
        var gson = Gson()
        val editor = file.edit()

        var strUsers = file.getString("USERS","")
        users = gson.fromJson(strUsers,typeUsers)

        var strUser = file.getString("USER", "")
        currentUser  = gson.fromJson(strUser,userType)

        binding.hello.text = "Hello "+currentUser.name+"!"

        if (currentUser.courses.isEmpty()){
            addCourse()
            currentUser.courses = courseList
        }else{
            courseList = currentUser.courses
        }




        var adapter = CourseAdapter(courseList,requireActivity(),
            object:CourseAdapter.CourseInterface{
                override fun onClick(course: Course) {
                    parentFragmentManager.beginTransaction().
                    replace(R.id.main, CourseItemFragment.newInstance(course)).commit()
                }

            })
        binding.recyclerHome.adapter = adapter

        var manager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
        binding.recyclerHome.layoutManager = manager



        binding.search.setOnClickListener{
            //findNavController().navigate(R.id.action_homeFragment2_to_searchFragment)
        }

        binding.seeAllCourses.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.parent_container,
            SeeAllFragment.newInstance("See all")).commit()
        }

        binding.wishlist.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.parent_container,
                SeeAllFragment.newInstance("Wishlist")).commit()
        }

        for (i in 0..users.size-1){
            if (users[i].name==currentUser.name){
                users[i] = currentUser
            }
        }

        strUsers = gson.toJson(users)
        editor.putString("USERS", strUsers)
        editor.apply()


        strUser = gson.toJson(currentUser)
        editor.putString("USER",strUser)
        editor.apply()



        return binding.root
    }

    fun addCourse(){
        courseList.add(Course("3D Design Basic",24.99, R.drawable.abstract3d))
        courseList.add(Course("Animation",24.99, R.drawable.abstract3d))
        courseList.add(Course("Design",24.99, R.drawable.abstract3d))
        courseList.add(Course("Product",24.99, R.drawable.abstract3d))
        courseList.add(Course("Game",24.99, R.drawable.abstract3d))
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}