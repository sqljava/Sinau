package com.example.sinau

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.sinau.databinding.FragmentProfileBinding
import com.example.sinau.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding : FragmentProfileBinding
    lateinit var file: SharedPreferences
    var userType = object : TypeToken<User>(){}.type
    lateinit var user : User


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
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        file = requireActivity().getSharedPreferences("FILE", Context.MODE_PRIVATE)
        var gson = Gson()
        val editor = file.edit()
        var struser = file.getString("USER", "")

        user = gson.fromJson(struser, userType)


        binding.profileName.text = user.name



        var dialog = Dialog(requireContext())
        var dialodView = layoutInflater.inflate(R.layout.dialog, null)
        var btnYes = dialodView.findViewById<Button>(R.id.btn_yes)
        var btnNo = dialodView.findViewById<Button>(R.id.btn_no)

        binding.logout.setOnClickListener {

            dialog.setContentView(dialodView)

            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            dialog.show()

            btnYes.setOnClickListener {
                editor.putBoolean("REMEMBER", false)
                editor.apply()
                parentFragmentManager.beginTransaction().replace(R.id.main,
                SignInFragment()).commit()
                dialog.hide()
            }

            btnNo.setOnClickListener {
                dialog.hide()
            }
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
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}