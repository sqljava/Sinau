package com.example.sinau

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sinau.databinding.FragmentCourseItemBinding
import com.example.sinau.model.Course

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
/**
 * A simple [Fragment] subclass.
 * Use the [CourseItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CourseItemFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var param1: Course

    lateinit var binding: FragmentCourseItemBinding

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

        var course = arguments?.getSerializable(ARG_PARAM1) as Course

        var name = course.name

        binding.courseName.text = name
        binding.courseImg.setImageResource(course.img)


        binding.back.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, ParentFragment()).commit()
        }

        binding.enrollButton.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.main,
                CongratsFragment()).commit()
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CourseItemFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Course) =
            CourseItemFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}