package com.example.javas.ui.homepage

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import com.example.javas.R
import com.example.javas.databinding.FragmentHomePageBinding
import com.example.javas.databinding.FragmentRegisterOneBinding


class HomePageFragment : Fragment() {

    private lateinit var  _binding: FragmentHomePageBinding
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = HomePageFragmentArgs.fromBundle(arguments as Bundle).name
        binding.homePageUserTitle.text = name

        binding.btnVaksinasiHomePage.setOnClickListener {
            view.findNavController().navigate(R.id.action_homePageFragment_to_symptomOneFragment)
        }
        binding.btnJadwalHomePage.setOnClickListener {

        }
        binding.btnProfileHomePage.setOnClickListener {
            val toUserProfile = HomePageFragmentDirections.actionHomePageFragmentToUserProfileFragment()
            toUserProfile.name = name
            view.findNavController().navigate(toUserProfile)
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