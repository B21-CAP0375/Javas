package com.example.javas.ui.register

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
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
        val name = RegisterTwoFragmentArgs.fromBundle(arguments as Bundle).name
        val nik = RegisterTwoFragmentArgs.fromBundle(arguments as Bundle).nik
        val dob = RegisterTwoFragmentArgs.fromBundle(arguments as Bundle).dob
        val place = RegisterTwoFragmentArgs.fromBundle(arguments as Bundle).birthPlace
        val gender = RegisterTwoFragmentArgs.fromBundle(arguments as Bundle).gender
        val backPressed = RegisterTwoFragmentArgs.fromBundle(arguments as Bundle).backPressed

        if (backPressed){
            binding.nameEdtxt.setText(name)
            binding.nikEdtxt.setText(nik)
            binding.dobEdtxt.setText(dob)
            binding.birthPlaceEdtxt.setText(place)
            binding.genderEdtxt.setText(gender)
        }


        binding.btnContinueLoginPage.setOnClickListener{
            val toRegisterThree = RegisterTwoFragmentDirections.actionRegisterTwoFragmentToRegisterThreeFragment()
            toRegisterThree.email = email
            toRegisterThree.password = password
            toRegisterThree.name = binding.nameEdtxt.text.toString()
            toRegisterThree.nik = binding.nikEdtxt.text.toString()
            toRegisterThree.dateOfBirth = binding.dobEdtxt.text.toString()
            toRegisterThree.bornPlace = binding.birthPlaceEdtxt.text.toString()
            toRegisterThree.kelamin = binding.genderEdtxt.text.toString()
            view.findNavController().navigate(toRegisterThree)
        }

        binding.btnBackRegisterPage.setOnClickListener{
            val toRegisterOne = RegisterTwoFragmentDirections.actionRegisterTwoFragmentToRegisterOneFragment()
            toRegisterOne.email = email
            toRegisterOne.password = password
            toRegisterOne.backpressed=true
            view.findNavController().navigate(toRegisterOne)
        }
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val toRegisterOne = RegisterTwoFragmentDirections.actionRegisterTwoFragmentToRegisterOneFragment()
                toRegisterOne.email = email
                toRegisterOne.password = password
                toRegisterOne.backpressed=true
                view.findNavController().navigate(toRegisterOne)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }



}