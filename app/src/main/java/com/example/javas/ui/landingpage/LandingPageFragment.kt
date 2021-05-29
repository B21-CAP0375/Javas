package com.example.javas.ui.landingpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.javas.R
import com.example.javas.databinding.FragmentLandingPageBinding
import com.example.javas.databinding.FragmentSplashScreenBinding


class LandingPageFragment : Fragment() {

    private lateinit var  _binding: FragmentLandingPageBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLandingPageBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnLogin.setOnClickListener {
            view.findNavController().navigate(R.id.action_landingPageFragment_to_loginFragment)
        }
        binding.btnRegister.setOnClickListener{
            view.findNavController().navigate(R.id.action_landingPageFragment_to_registerOneFragment)
        }
        binding.btnLoginAdminPage.setOnClickListener {
            view.findNavController().navigate(R.id.action_landingPageFragment_to_adminPageFragment)
        }
    }

}