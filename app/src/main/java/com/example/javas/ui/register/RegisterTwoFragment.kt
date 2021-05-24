package com.example.javas.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.javas.R
import com.example.javas.databinding.FragmentRegisterOneBinding
import com.example.javas.databinding.FragmentRegisterTwoBinding


class RegisterTwoFragment : Fragment() {

    private lateinit var  _binding: FragmentRegisterTwoBinding
    private val binding get() = _binding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterTwoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val email = RegisterTwoFragmentArgs.fromBundle(arguments as Bundle).email
        val password = RegisterTwoFragmentArgs.fromBundle(arguments as Bundle).password

        binding.btnContinueLoginPage.setOnClickListener{
            val toRegisterThree = RegisterTwoFragmentDirections.actionRegisterTwoFragmentToRegisterThreeFragment()
            toRegisterThree.email = email
            toRegisterThree.password = password
            toRegisterThree.name = binding.nameEdtxt.text.toString()
            toRegisterThree.nik = binding.nikEdtxt.text.toString()
            toRegisterThree.dateOfBirth = binding.dobEdtxt.text.toString()
            toRegisterThree.bornPlace = binding.birthPlaceEdtxt.text.toString()

            view.findNavController().navigate(toRegisterThree)
        }

    }


}