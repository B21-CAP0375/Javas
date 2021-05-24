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
            Toast.makeText(context, "Login as ${binding.emailEdtxt.text} with password ${binding.passwordEdtxt.text}", Toast.LENGTH_SHORT).show()
        }
        binding.btnRegisterLoginPage.setOnClickListener{
            Toast.makeText(context, "Register btn", Toast.LENGTH_SHORT).show()
        }
    }


}