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
        val dobDay = RegisterTwoFragmentArgs.fromBundle(arguments as Bundle).day
        val dobMonth = RegisterTwoFragmentArgs.fromBundle(arguments as Bundle).month
        val dobYear = RegisterTwoFragmentArgs.fromBundle(arguments as Bundle).year
        val place = RegisterTwoFragmentArgs.fromBundle(arguments as Bundle).birthPlace
        val gender = RegisterTwoFragmentArgs.fromBundle(arguments as Bundle).gender
        val backPressed = RegisterTwoFragmentArgs.fromBundle(arguments as Bundle).backPressed

        if (backPressed){
            binding.nameEdtxt.setText(name)
            binding.nikEdtxt.setText(nik)
            binding.dobEdtxtDay.setText(dobDay)
            binding.dobEdtxtMonth.setText(dobMonth)
            binding.dobEdtxtYear.setText(dobYear)
            binding.birthPlaceEdtxt.setText(place)
            binding.genderEdtxt.setText(gender)
        }


        binding.btnContinueLoginPage.setOnClickListener{
            val date = binding.dobEdtxtDay.text.toString() +binding.dobEdtxtMonth.text.toString() +binding.dobEdtxtYear.text.toString()
            val toRegisterThree = RegisterTwoFragmentDirections.actionRegisterTwoFragmentToRegisterThreeFragment()
            toRegisterThree.email = email
            toRegisterThree.password = password
            toRegisterThree.name = binding.nameEdtxt.text.toString()
            toRegisterThree.nik = binding.nikEdtxt.text.toString()
            toRegisterThree.dateOfBirth = date
            toRegisterThree.day= binding.dobEdtxtDay.text.toString()
            toRegisterThree.month= binding.dobEdtxtMonth.text.toString()
            toRegisterThree.year= binding.dobEdtxtYear.text.toString()
            toRegisterThree.bornPlace = binding.birthPlaceEdtxt.text.toString()
            toRegisterThree.kelamin = binding.genderEdtxt.text.toString()
            if (validate()){
                view.findNavController().navigate(toRegisterThree)
            }
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


    private fun validate():Boolean{
        var  valid = true
        val nameCheck = binding.nameEdtxt.text
        val nikCheck = binding.nikEdtxt.text
        val dobDayCheck = binding.dobEdtxtDay.text
        val dobMonthCheck = binding.dobEdtxtMonth.text
        val dobYearCheck = binding.dobEdtxtYear.text

       val placeCheck = binding.birthPlaceEdtxt.text
        val genderCheck = binding.genderEdtxt.text


        if (nameCheck.isEmpty()) {
            valid=false
        }
        if (nikCheck.isEmpty()||nikCheck.toString().length !=16){
            valid=false
        }

        if (dobDayCheck.isEmpty()||dobMonthCheck.isEmpty()||dobYearCheck.isEmpty()
            ||dobDayCheck.toString().toInt()>31||dobMonthCheck.toString().toInt()>12
            ||dobYearCheck.toString().toInt()<1980||dobYearCheck.toString().toInt()>2021){
            valid=false
        }

        if (placeCheck.isEmpty()){
            valid=false
        }
        if (genderCheck.isEmpty()){
            valid=false
        }
        if (genderCheck.toString()!="pria"&& genderCheck.toString()!="wanita"){
            valid=false
        }
        return valid
    }


}