package com.example.javas.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.javas.R
import com.example.javas.databinding.FragmentLandingPageBinding
import com.example.javas.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {


    private lateinit var  _binding: FragmentLoginBinding
    private val binding get() = _binding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnLoginLoginPage.setOnClickListener {
            if (validate()){
                view.findNavController().navigate(R.id.action_loginFragment_to_homePageFragment)
            }
        }
        binding.btnRegisterLoginPage.setOnClickListener{
            view.findNavController().navigate(R.id.action_loginFragment_to_registerOneFragment)
        }
    }

    private fun validate():Boolean{
        var  valid = true
        val emailCheck = binding.emailEdtxt.text
        val passCheck = binding.passwordEdtxt.text

        //passcheck dan emailcheck mendapatkan data dari api
        if (emailCheck.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailCheck).matches()) {
            valid=false
        }
        if (passCheck.isEmpty() || passCheck.length < 6 || passCheck.length > 10){
            valid=false
        }
        return valid
    }

}