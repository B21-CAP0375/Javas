package com.example.javas.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.javas.R
import com.example.javas.databinding.FragmentRegisterOneBinding
import com.example.javas.databinding.FragmentRegisterThreeBinding
import com.example.javas.utils.ViewModelFactory


class RegisterThreeFragment : Fragment() {

    private lateinit var  _binding: FragmentRegisterThreeBinding
    private val binding get() = _binding
    private lateinit var  viewModel: RegisterViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterThreeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[RegisterViewModel::class.java]

        val email = RegisterThreeFragmentArgs.fromBundle(arguments as Bundle).email
        val password = RegisterThreeFragmentArgs.fromBundle(arguments as Bundle).password
        val name = RegisterThreeFragmentArgs.fromBundle(arguments as Bundle).name
        val nik = RegisterThreeFragmentArgs.fromBundle(arguments as Bundle).nik
        val dob = RegisterThreeFragmentArgs.fromBundle(arguments as Bundle).dateOfBirth
        val place = RegisterThreeFragmentArgs.fromBundle(arguments as Bundle).bornPlace
        val gender = RegisterThreeFragmentArgs.fromBundle(arguments as Bundle).kelamin
        val day = RegisterThreeFragmentArgs.fromBundle(arguments as Bundle).day
        val month = RegisterThreeFragmentArgs.fromBundle(arguments as Bundle).month
        val year = RegisterThreeFragmentArgs.fromBundle(arguments as Bundle).year





        //sebelum pindah masukan data registrasi ke dalam server
        binding.btnRegisterRegisterPage.setOnClickListener {
            val users = hashMapOf(
                "email" to email,
                "password" to password,
                "name" to name,
                "nik" to nik,
                "dob" to dob,
                "place" to place,
                "gender" to gender,
                "phone" to binding.phoneEdtxt.text.toString(),
                "alamat" to binding.alamatEdtxt.text.toString(),
                "role" to "user"
            )
//            val symptom = hashMapOf(
//                "covid-19" to false
//            )
//            val vaccineDate = hashMapOf(
//                "vaccineDate" to " "
//            )

            val toHomePage = RegisterThreeFragmentDirections.actionRegisterThreeFragmentToHomePageFragment()
            toHomePage.name=email
            if (validate()){
                activity?.let {
                    viewModel.register(email, password)
                        .addOnCompleteListener(it) { task ->
                            if (task.isSuccessful) {
                                viewModel.createUser(email)
                                    .set(users)

//                                viewModel.createUser(email)
//                                    .collection("vaccination")
//                                    .document("symptom")
//                                    .set(symptom)
//
//                                viewModel.createUser(email)
//                                    .collection("vaccination")
//                                    .document("vaccineDate")
//                                    .set(vaccineDate)
                                // Sign in success, update UI with the signed-in user's information
                                view.findNavController().navigate(toHomePage)
                                // If sign in fails, display a message to the user.
                            }
                        }
                }
            }
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
            toRegisterTwo.day=day
            toRegisterTwo.month=month
            toRegisterTwo.year=year
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
                toRegisterTwo.day=day
                toRegisterTwo.month=month
                toRegisterTwo.year=year
                toRegisterTwo.backPressed=true
                view.findNavController().navigate(toRegisterTwo)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun validate():Boolean{
        var  valid = true
        val phoneCheck = binding.phoneEdtxt.text
        val addressCheck = binding.alamatEdtxt.text

        if (phoneCheck.isEmpty()||phoneCheck.toString().length!=12){
            valid =false
        }
        if (addressCheck.isEmpty()){
            valid=false
        }
        return valid
    }
}