package com.example.javas.ui.datepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.javas.R
import com.example.javas.databinding.FragmentDatePageBinding
import com.example.javas.databinding.FragmentUserProfileBinding
import com.example.javas.ui.adminlistdate.AdminListDateViewModel
import com.example.javas.ui.userprofile.UserProfileViewModel
import com.example.javas.utils.ViewModelFactory


class DatePageFragment : Fragment() {



    private lateinit var  _binding: FragmentDatePageBinding
    private val binding get() = _binding
    private lateinit var  viewModel: DatePageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDatePageBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[DatePageViewModel::class.java]

        val name = DatePageFragmentArgs.fromBundle(arguments as Bundle).name

        val dateVaccine = viewModel.getVaccineDate(name)

        dateVaccine
            .get()
            .addOnSuccessListener {
                document ->
                binding.dateEdt.text=document.getString("vaccineDate").toString()
                binding.hospitalEdt.text=document.getString("hospital").toString()
            }
        super.onViewCreated(view, savedInstanceState)
    }
}