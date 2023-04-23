package com.example.sinau

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.example.sinau.databinding.FragmentSplashBinding
import com.google.gson.Gson

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashFragment : Fragment(), Animation.AnimationListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentSplashBinding
    lateinit var file: SharedPreferences

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
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        file = requireActivity().getSharedPreferences("FILE", Context.MODE_PRIVATE)
        var anim = AnimationUtils.loadAnimation(requireContext(),R.anim.splash_anim)
        var imgAnim = AnimationUtils.loadAnimation(requireContext(),R.anim.splash_anim)
        imgAnim.setAnimationListener(this)

        var isRemember = file.getBoolean("REMEMBER",false)

        if (isRemember){
            binding.splashText.visibility = View.INVISIBLE
            binding.splashButton.visibility = View.INVISIBLE
            binding.imageView.startAnimation(imgAnim)

        }else{
            binding.splashButton.startAnimation(anim)
            binding.splashText.startAnimation(anim)
        }

        binding.splashButton.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.main,
                SignInFragment()).commit()
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
         * @return A new instance of fragment SplashFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SplashFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onAnimationStart(animation: Animation?) {

    }

    override fun onAnimationEnd(animation: Animation?) {
        parentFragmentManager.beginTransaction().replace(R.id.main,
            ParentFragment()).commit()
    }

    override fun onAnimationRepeat(animation: Animation?) {

    }
}