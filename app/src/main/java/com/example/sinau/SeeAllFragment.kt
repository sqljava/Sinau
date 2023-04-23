package com.example.sinau

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sinau.adapter.CourseAdapter
import com.example.sinau.databinding.FragmentSeeAllBinding
import com.example.sinau.model.Course
import com.example.sinau.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"


/**
 * A simple [Fragment] subclass.
 * Use the [SeeAllFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SeeAllFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null

    lateinit var binding: FragmentSeeAllBinding

    lateinit var file: SharedPreferences
    lateinit var currentUser: User
    var userType = object : TypeToken<User>(){}.type

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeeAllBinding.inflate(inflater, container, false)

        file = requireActivity().getSharedPreferences("FILE", Context.MODE_PRIVATE)
        var gson = Gson()

        var strUser = file.getString("USER", "")

        currentUser = gson.fromJson(strUser,userType)

        var list = currentUser.courses

        var title = arguments?.getString(ARG_PARAM1)
        binding.moreText.text = title

        var adapter = CourseAdapter(list, requireActivity())

        if (title=="Wishlist"){
            var wlist = mutableListOf<Course>()
            for (i in list){
                if (i.liked){
                    wlist.add(i)
                }
            }
            adapter = CourseAdapter(wlist,requireActivity())
        }

        var manager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        binding.seeAllRecycler.adapter = adapter
        binding.seeAllRecycler.layoutManager = manager

        binding.back.setOnClickListener {
            parentFragmentManager.beginTransaction().
            replace(R.id.parent_container, HomeFragment()).commit()
        }


        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SeeAllFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(title: String) =
            SeeAllFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, title)
                }
            }
    }
}