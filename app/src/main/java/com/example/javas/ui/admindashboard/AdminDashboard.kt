package com.example.javas.ui.admindashboard

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import com.example.javas.R
import com.example.javas.databinding.FragmentAdminDashboardBinding
import com.example.javas.databinding.FragmentHomePageBinding


class AdminDashboard : Fragment() {

    private lateinit var  _binding: FragmentAdminDashboardBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAdminDashboardBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val hospital = AdminDashboardArgs.fromBundle(arguments as Bundle).hospital
        binding.homePageUserTitle.text = hospital


        binding.btnVaksinasiHomePage.setOnClickListener {
            val toDate = AdminDashboardDirections.actionAdminDashboardToHospitalVaksinasiFragment()
            toDate.hospital=hospital
            view.findNavController().navigate(toDate)
        }

        binding.btnJadwalHomePage.setOnClickListener {

        }
    }



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