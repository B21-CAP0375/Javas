package com.example.javas.ui.homepage

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.javas.databinding.FragmentHomePageBinding
import com.example.javas.ui.datepage.DatePageViewModel
import com.example.javas.utils.ViewModelFactory


class HomePageFragment : Fragment() {

    private lateinit var  _binding: FragmentHomePageBinding
    private val binding get() = _binding
    private lateinit var  viewModel: HomePageViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[HomePageViewModel::class.java]


        val name = HomePageFragmentArgs.fromBundle(arguments as Bundle).name
        binding.homePageUserTitle.text = name

        binding.btnVaksinasiHomePage.setOnClickListener {
            viewModel.getVaccineDate(name)
                .get()
                .addOnSuccessListener {
                    document->
                    val date = document.getString("vaccineDate")
                    if (date !=null){
                        Toast.makeText(context, "Anda sudah terdaftar vaksin pada $date ", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        val toSymptom = HomePageFragmentDirections.actionHomePageFragmentToSymptomOneFragment()
                        toSymptom.name= name
                        view.findNavController().navigate(toSymptom)
                    }
                }
        }
        binding.btnJadwalHomePage.setOnClickListener {
            val toDatePage = HomePageFragmentDirections.actionHomePageFragmentToDatePageFragment()
            toDatePage.name= name
            view.findNavController().navigate(toDatePage)
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