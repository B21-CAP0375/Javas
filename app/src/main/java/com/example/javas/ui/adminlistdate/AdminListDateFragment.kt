package com.example.javas.ui.adminlistdate

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.javas.R
import com.example.javas.databinding.FragmentAdminListDateBinding
import com.example.javas.utils.ViewModelFactory

class AdminListDateFragment : Fragment(), AdapterView.OnItemSelectedListener {

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
        val spinner = binding.spinner
        val subjects: MutableList<String> = ArrayList()

        viewModel.getUser(hospital)
            .get()
            .addOnSuccessListener { documents->
                for (document in documents) {
                    subjects.add(document.getString("date").toString())
                    val arrayAdapter =
                        context?.let { ArrayAdapter(it,android.R.layout.simple_spinner_item,subjects) }
                    if (arrayAdapter != null) {
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinner.adapter = arrayAdapter
                    }
                }
            }
    }



        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            Toast.makeText(context, "coba", Toast.LENGTH_SHORT).show()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(context, "gagal", Toast.LENGTH_SHORT).show()
        }

}



