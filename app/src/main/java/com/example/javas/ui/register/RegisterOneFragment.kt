package com.example.javas.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.javas.R
import com.example.javas.databinding.FragmentLoginBinding
import com.example.javas.databinding.FragmentRegisterOneBinding


class RegisterOneFragment : Fragment() {

    private lateinit var  _binding: FragmentRegisterOneBinding
    private val binding get() = _binding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterOneBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnContinueLoginPage.setOnClickListener {
            val toRegisterTwo = RegisterOneFragmentDirections.actionRegisterOneFragmentToRegisterTwoFragment()
            toRegisterTwo.email = binding.emailEdtxt.text.toString()
            toRegisterTwo.password = binding.passwordEdtxt.text.toString()

            view.findNavController().navigate(toRegisterTwo)
        }
        binding.btnBackRegisterPage.setOnClickListener{
        }
    }

}