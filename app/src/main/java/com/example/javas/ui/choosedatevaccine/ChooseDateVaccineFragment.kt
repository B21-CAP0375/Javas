package com.example.javas.ui.choosedatevaccine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.javas.R
import com.example.javas.databinding.FragmentAdminListDateBinding
import com.example.javas.databinding.FragmentChooseDateVaccineBinding
import com.example.javas.ui.adminlistdate.AdminListDateViewModel
import com.example.javas.utils.ViewModelFactory

class ChooseDateVaccineFragment : Fragment() {

    private lateinit var  _binding: FragmentChooseDateVaccineBinding
    private val binding get() = _binding
    private lateinit var  viewModel: ChooseDateVaccineViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentChooseDateVaccineBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val name = ChooseDateVaccineFragmentArgs.fromBundle(arguments as Bundle).name
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[ChooseDateVaccineViewModel::class.java]

        val getDate = viewModel.getDate("hospitaltesting")
        val getUser = viewModel.getUser(name)

        val spinner = binding.spinner
        val subjects: MutableList<String> = ArrayList()


        getDate
            .addOnSuccessListener { documents->
                for (document in documents) {
                    subjects.add(document.getString("date").toString())
                    val arrayAdapter =
                        context?.let { ArrayAdapter(it,android.R.layout.simple_spinner_item,subjects) }
                    if (arrayAdapter != null) {
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinner.adapter = arrayAdapter
                        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                            override fun onNothingSelected(parent: AdapterView<*>?) {
                                Toast.makeText(context, "tidak bisa", Toast.LENGTH_SHORT).show()
                            }
                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long
                            ) {
                                val vaccineDate = hashMapOf(
                                         "vaccineDate" to spinner.getItemAtPosition(position).toString(),
                                         "hospital" to "hospitaltesting"
                                )
                                binding.btnLoginLoginPage.setOnClickListener {
                                    getUser
                                        .collection("vaccination")
                                        .document("vaccineDate")
                                        .set(vaccineDate)
                                    Toast.makeText(context, "berhasil", Toast.LENGTH_SHORT).show()
                                    val toHomePage = ChooseDateVaccineFragmentDirections.actionChooseDateVaccineFragmentToHomePageFragment()
                                    toHomePage.name=name
                                    if (view != null) {

                                        viewModel.setHospital("hospitaltesting",spinner.getItemAtPosition(position).toString())
                                            .get()
                                            .addOnSuccessListener {
                                                documents->   var maxPerson =
                                                documents.getString("maxPerson")?.toInt() ?: 0
                                                var status=true
                                                if (maxPerson!=0){
                                                    maxPerson -= 1
                                                    if (maxPerson==0){
                                                        status= false
                                                    }
                                                }
                                               val a = maxPerson.toString()
                                                val hospital = hashMapOf(
                                                    "date" to spinner.getItemAtPosition(position).toString(),
                                                    "maxPerson" to a,
                                                    "status" to status
                                                )
                                                viewModel.reduceUser("hospitaltesting",spinner.getItemAtPosition(position).toString())
                                                    .set(hospital)
                                            }
                                        view.findNavController().navigate(toHomePage)
                                    }
                                }
                            }
                        }
                    }
                }
            }
    }
}