package com.example.javas.ui.splashscreen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.javas.R
import com.example.javas.databinding.FragmentSplashScreenBinding


class SplashScreenFragment : Fragment()  {

    private lateinit var handler:Handler

    private lateinit var  _binding:FragmentSplashScreenBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            view.findNavController().navigate(R.id.action_splashScreenFragment_to_landingPageFragment)
        },2000)

    }
}