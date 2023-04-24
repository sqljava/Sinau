package com.example.sinau

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import com.example.sinau.adapter.CourseAdapter
import com.example.sinau.databinding.FragmentSearchBinding
import com.example.sinau.model.Course
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentSearchBinding

    var courseList = mutableListOf<Course>()
    lateinit var file: SharedPreferences
    var courseListType = object : TypeToken<List<Course>>(){}.type

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
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        file = requireActivity().getSharedPreferences("FILE", Context.MODE_PRIVATE)
        var gson = Gson()
        //val editor = file.edit()

        var strCourses = file.getString("COURSES", "")
        courseList = gson.fromJson(strCourses, courseListType)

        var adapter = CourseAdapter(courseList,requireActivity(),
            object:CourseAdapter.CourseInterface{
                override fun onClick(course: Course) {
                    parentFragmentManager.beginTransaction().
                    replace(R.id.main, CourseItemFragment()).commit()
                }

            })
        binding.searchRecycler.adapter = adapter

//        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(p0: String?): Boolean {
//                binding.search.clearFocus()
//                var list = mutableListOf<Course>()
//
//                for (i in courseList){
//                    if (i.name.contains(p0.toString())){
//                        list.add(i)
//                    }
//                }
//                binding.searchRecycler.adapter = CourseAdapter(list,requireActivity())
//
//                return false
//            }
//
//            override fun onQueryTextChange(p0: String?): Boolean {
//                binding.search.clearFocus()
//                var list = mutableListOf<Course>()
//
//                for (i in courseList){
//                    if (i.name.contains(p0.toString())){
//                        list.add(i)
//                    }
//                }
//                binding.searchRecycler.adapter = CourseAdapter(list,requireActivity())
//
//                return false
//            }
//        })


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}