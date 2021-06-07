package com.example.javas.ui.register

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import com.example.javas.R
import com.example.javas.databinding.FragmentRegisterOneBinding


class RegisterOneFragment : Fragment() {

    private lateinit var  _binding: FragmentRegisterOneBinding
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterOneBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val backPressed = RegisterOneFragmentArgs.fromBundle(arguments as Bundle).backpressed
        if (backPressed){
            val email = RegisterOneFragmentArgs.fromBundle(arguments as Bundle).email
            val password = RegisterOneFragmentArgs.fromBundle(arguments as Bundle).password
            binding.passwordEdtxt.setText(password)
            binding.emailEdtxt.setText(email)
        }

        binding.btnContinueLoginPage.setOnClickListener {
            val toRegisterTwo = RegisterOneFragmentDirections.actionRegisterOneFragmentToRegisterTwoFragment()
            toRegisterTwo.email = binding.emailEdtxt.text.toString()
            toRegisterTwo.password = binding.passwordEdtxt.text.toString()

            if (validate()){
                view.findNavController().navigate(toRegisterTwo)
            }else {
                Toast.makeText(context, "Mohon jawab semua pertanyaan", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnBackRegisterPage.setOnClickListener{
            view.findNavController().navigate(R.id.action_registerOneFragment_to_landingPageFragment)
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                view?.findNavController()?.navigate(R.id.action_registerOneFragment_to_landingPageFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun validate():Boolean{
        var valid = true
        val emailCheck = binding.emailEdtxt.text
        val passCheck = binding.passwordEdtxt.text


        if (emailCheck.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailCheck).matches()) {
            binding.emailEdtxt.error = "Email tidak valid"
            valid=false
        }
        if (passCheck.isEmpty() || passCheck.length < 6 || passCheck.length > 10){
            binding.passwordEdtxt.error = "Mohon isi 6 sampai 10 karakter"
            valid=false
        }
        return valid
    }

}