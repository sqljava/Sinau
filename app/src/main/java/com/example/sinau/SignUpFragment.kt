package com.example.sinau

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sinau.databinding.FragmentSignUpBinding
import com.example.sinau.model.User
import com.google.gson.Gson
import com.google.gson.ToNumberStrategy
import com.google.gson.reflect.TypeToken

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentSignUpBinding
    lateinit var file: SharedPreferences
    var typeUsers = object : TypeToken<List<User>>(){}.type
    var userList = mutableListOf<User>()

    lateinit var userName:String
    lateinit var userPassword:String
    lateinit var currentUser : User

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
        binding = FragmentSignUpBinding.inflate(inflater,container,false)



        file = requireActivity().getSharedPreferences("FILE", Context.MODE_PRIVATE)
        var gson = Gson()
        val editor = file.edit()

        var strUsers = file.getString("USERS","")
        var strUser = ""

        binding.btnSignUp.setOnClickListener {
            if(!areFieldsEmpty()){
                if (strUsers==""){

                    userList.add(currentUser)
                    strUsers = gson.toJson(userList)
                    editor.putString("USERS",strUsers)
                    strUser = gson.toJson(currentUser)
                    editor.putString("USER", strUser)
                    editor.apply()
                    parentFragmentManager.beginTransaction().replace(R.id.main,
                        ParentFragment()).commit()
                }else{
                    userList = gson.fromJson(strUsers,typeUsers)
                    for (i in userList){
                        if (currentUser==i){
                            println("this acc already exists")
                        }else{
                            userList.add(currentUser)
                            strUsers = gson.toJson(userList)
                            editor.putString("USERS",strUsers)
                            strUser = gson.toJson(currentUser)
                            editor.putString("USER", strUser)
                            editor.apply()
                            parentFragmentManager.beginTransaction().replace(R.id.main,
                                ParentFragment()).commit()
                        }
                    }
                }
            }
        }

        binding.signInText.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.main, SignInFragment()).commit()        }

        return binding.root
    }

    private fun areFieldsEmpty():Boolean{
        userName = binding.signUpName.text.toString()
        userPassword = binding.signUpPassword.text.toString()

        currentUser = User(userName,userPassword)

        if (userName.isEmpty()){
            binding.bigSignUpName.helperText = "Please fill"
        }else{
            binding.bigSignUpName.helperText = "Your name"
        }
        if(userPassword.isEmpty()){
            binding.bigSignUpPassword.helperText = "Please fill"
        }else{
            binding.bigSignUpPassword.helperText = "Password"
        }
        return userName.isEmpty() or (userPassword.isEmpty())
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignUpFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUpFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}