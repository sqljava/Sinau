package com.example.sinau

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sinau.databinding.FragmentParentBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



/**
 * A simple [Fragment] subclass.
 * Use the [ParentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ParentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentParentBinding

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
        binding = FragmentParentBinding.inflate(inflater, container, false)


        //val navController = findNavController()

        binding.navBar.setupWithNavController(findNavController())




//        binding.navBar.setOnItemReselectedListener {
//
//            when(it.itemId){
//                R.id.homeFragment->{
//                    replaceFragment(HomeFragment())
//                }
//                R.id.courseFragment->{
//                    replaceFragment(CourseFragment())
//                }
//                R.id.chatFragment->{
//                    replaceFragment(ChatFragment())
//                }
//                R.id.profileFragment->{
//                    //findNavController()
//
//                }
//
//            }
//            true
//        }


        return binding.root
    }

    private fun replaceFragment(fragment: Fragment){
        var changer = parentFragmentManager.beginTransaction()

        changer.replace(R.id.parent_container, fragment)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ParentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ParentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}