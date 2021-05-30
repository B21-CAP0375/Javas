package com.example.javas.ui.adminlistdate

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.javas.R
import com.example.javas.databinding.FragmentAdminListDateBinding
import com.example.javas.databinding.FragmentHospitalVaksinasiBinding
import com.example.javas.ui.adminpage.AdminPageViewModel
import com.example.javas.utils.ViewModelFactory

class AdminListDateFragment : Fragment() {

    private lateinit var  _binding: FragmentAdminListDateBinding
    private val binding get() = _binding
    private lateinit var  viewModel: AdminListDateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        _binding = FragmentAdminListDateBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[AdminListDateViewModel::class.java]

        val hospital = AdminListDateFragmentArgs.fromBundle(arguments as Bundle).hospital
        viewModel.getUser(hospital)
            .get()
            .addOnSuccessListener { documents->
                for (document in documents) {
                    val a = "${document.id} => ${document.data}"
                    binding.textTitle.text= a
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
        super.onViewCreated(view, savedInstanceState)
    }

}