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

            view.findNavController().navigate(toRegisterTwo)
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

    //jika ingin memberi animasi pada tombol on back pressed
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        val callback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                getActivity()?.moveTaskToBack(true);
//                getActivity()?.finish();
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
//    }

}