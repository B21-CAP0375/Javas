package com.example.javas.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.javas.R
import com.example.javas.databinding.FragmentRegisterOneBinding


class RegisterThreeFragment : Fragment() {

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
        val email = RegisterThreeFragmentArgs.fromBundle(arguments as Bundle).email
        val password = RegisterThreeFragmentArgs.fromBundle(arguments as Bundle).password
        val name = RegisterThreeFragmentArgs.fromBundle(arguments as Bundle).name
        val nik = RegisterThreeFragmentArgs.fromBundle(arguments as Bundle).nik
        val dob = RegisterThreeFragmentArgs.fromBundle(arguments as Bundle).dateOfBirth
        val place = RegisterThreeFragmentArgs.fromBundle(arguments as Bundle).bornPlace



        binding.btnContinueLoginPage.setOnClickListener {

        }
    }
}