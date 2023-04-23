package com.example.sinau

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.sinau.databinding.FragmentSignInBinding
import com.example.sinau.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.DEBUG_PROPERTY_NAME

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignInFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignInFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentSignInBinding
    lateinit var file:SharedPreferences
    var type = object : TypeToken<List<User>>(){}.type
    var userList = mutableListOf<User>()
    lateinit var currentUser : User

    lateinit var userName:String
    lateinit var userPassword:String

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
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        file = requireActivity().getSharedPreferences("FILE", Context.MODE_PRIVATE)
        var editor = file.edit()
        var gson = Gson()

        var str = file.getString("USERS","")

        binding.btnSignIn.setOnClickListener {

            if (!areFieldsEmpty()){
                if (isDataCorrect()){
                    if (str==""){

                    }
                    else{
                        userList = gson.fromJson(str, type)
                        var user = User(userName, userPassword)
                        for (i in userList){
                            if (user.name==i.name && user.password==i.password){
                                currentUser = i
                                var u = gson.toJson(currentUser)
                                if (binding.signInCheckBox.isChecked){
                                    editor.putBoolean("REMEMBER",true)
                                }
                                editor.putString("USER",u)
                                editor.apply()
                                parentFragmentManager.beginTransaction().replace(R.id.main,
                                    ParentFragment()).commit()
                            }
                        }
                    }
                }
            }
        }

        binding.signUpText.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.main,
            SignUpFragment()).commit()
        }

        return binding.root
    }

    private fun areFieldsEmpty():Boolean{
        userName = binding.signInName.text.toString()
        userPassword = binding.signInPassword.text.toString()

        if (userName.isEmpty()){
            binding.bigSignInName.helperText = "Please fill"

        }else{
            binding.bigSignInName.helperText = "Your name"
        }
        if(userPassword.isEmpty()){
            binding.bigSignInPassword.helperText = "Please fill"
        }else{
            binding.bigSignInPassword.helperText = "Password"
        }
        return userName.isEmpty() or (userPassword.isEmpty())
    }

    fun isDataCorrect():Boolean{
        var name = userName
        var pass = userPassword
        var result = true

        for (i in name){
            if (i==' '){
                binding.inHelperText.text = "Write name without ' ' "
                result = false
            }
        }

        for (i in pass){
            if (i==' '){
                binding.inHelperText.text = "Write password without ' ' "
                result = false
            }
        }

        if (name[0].isDigit()){
            binding.inHelperText.text = "Don`t start name with number"
            result = false
        }

        return result
    }



    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignInFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}