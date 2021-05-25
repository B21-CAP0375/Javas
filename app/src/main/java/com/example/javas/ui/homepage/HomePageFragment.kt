package com.example.javas.ui.homepage

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.javas.R
import com.example.javas.databinding.FragmentHomePageBinding
import com.example.javas.databinding.FragmentRegisterOneBinding


class HomePageFragment : Fragment() {

    private lateinit var  _binding: FragmentHomePageBinding
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = HomePageFragmentArgs.fromBundle(arguments as Bundle).name
        binding.homePageUserTitle.text = name

        binding.btnProfileHomePage.setOnClickListener{

        }
        binding.btnJadwalHomePage.setOnClickListener{

        }
        binding.btnProfileHomePage.setOnClickListener{

        }
    }
        //jika ingin memberi animasi pada tombol on back pressed
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.moveTaskToBack(true)
                activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

}