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


/**
 * A simple [Fragment] subclass.
 * Use the [ParentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ParentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null


    lateinit var binding: FragmentParentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentParentBinding.inflate(inflater, container, false)
        replaceFragment(HomeFragment())

        var a = param1

        when(a){
            1->{
                replaceFragment(HomeFragment())
            }
            2->{
                replaceFragment(CourseFragment())
            }
            3->{
                replaceFragment(ChatFragment())
            }
            4->{
                replaceFragment(ProfileFragment())
            }
        }

        binding.navBar.setOnItemSelectedListener {

            when(it.itemId){
                R.id.homeFragment->{
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.courseFragment->{
                    replaceFragment(CourseFragment())
                    true
                }
                R.id.chatFragment->{
                    replaceFragment(ChatFragment())
                    true
                }
                R.id.profileFragment->{
                    replaceFragment(ProfileFragment())
                    true
                }
            }
            true
        }


        return binding.root
    }

    private fun replaceFragment(fragment: Fragment){
        var changer = parentFragmentManager.beginTransaction()

        changer.replace(R.id.parent_container, fragment).commit()
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
        fun newInstance(param1: Int) =
            ParentFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}