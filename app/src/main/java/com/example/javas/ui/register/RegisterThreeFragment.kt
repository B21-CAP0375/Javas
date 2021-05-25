package com.example.javas.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.javas.R
import com.example.javas.databinding.FragmentRegisterOneBinding
import com.example.javas.databinding.FragmentRegisterThreeBinding


class RegisterThreeFragment : Fragment() {

    private lateinit var  _binding: FragmentRegisterThreeBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterThreeBinding.inflate(inflater, container, false)
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
        val gender = RegisterThreeFragmentArgs.fromBundle(arguments as Bundle).kelamin
        val phone = RegisterThreeFragmentArgs.fromBundle(arguments as Bundle).phone
        val address = binding.alamatEdtxt.text.toString()




        binding.btnContinueLoginPage.setOnClickListener {
            Toast.makeText(context, "$email", Toast.LENGTH_SHORT).show()
        }

        binding.btnBackRegisterPage.setOnClickListener{
            val toRegisterTwo = RegisterThreeFragmentDirections.actionRegisterThreeFragmentToRegisterTwoFragment()
            toRegisterTwo.email=email
            toRegisterTwo.password=password
            toRegisterTwo.name=name
            toRegisterTwo.nik=nik
            toRegisterTwo.dob=dob
            toRegisterTwo.birthPlace=place
            toRegisterTwo.gender=gender
            toRegisterTwo.backPressed=true
            view.findNavController().navigate(toRegisterTwo)
        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val toRegisterTwo = RegisterThreeFragmentDirections.actionRegisterThreeFragmentToRegisterTwoFragment()
                toRegisterTwo.email=email
                toRegisterTwo.password=password
                toRegisterTwo.name=name
                toRegisterTwo.nik=nik
                toRegisterTwo.dob=dob
                toRegisterTwo.birthPlace=place
                toRegisterTwo.gender=gender
                toRegisterTwo.backPressed=true
                view.findNavController().navigate(toRegisterTwo)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
}