package com.example.javas.ui.hospitalvaksinasi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.javas.databinding.FragmentAdminPageBinding
import com.example.javas.databinding.FragmentHospitalVaksinasiBinding
import com.example.javas.ui.adminpage.AdminPageViewModel
import com.example.javas.utils.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.*


class HospitalVaksinasiFragment : Fragment() {


    private lateinit var  _binding: FragmentHospitalVaksinasiBinding
    private val binding get() = _binding
    private lateinit var  viewModel: HospitalVaksinasiViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHospitalVaksinasiBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[HospitalVaksinasiViewModel::class.java]

        binding.btnUpdateVaccinateDatePage.setOnClickListener {
            val dobDay = binding.dobEdtxtDay.text.toString()
            val dobMonth = binding.dobEdtxtMonth.text.toString()
            val dobYear = binding.dobEdtxtYear.text.toString()
            val date="$dobYear-$dobMonth-$dobDay"
            val maxPerson = binding.personEdtxtMax.text.toString()



            val hospitalName = HospitalVaksinasiFragmentArgs.fromBundle(arguments as Bundle).hospital
            if (validate()){
                val hospital = hashMapOf(
                    "date" to date,
                    "maxPerson" to maxPerson,
                    "setPerson" to maxPerson,
                    "status" to true
                )
                Toast.makeText(context, "Data berhasil masuk", Toast.LENGTH_SHORT).show()
                viewModel.setHospital(hospitalName,date).set(hospital)
            }
        }
    }

    private fun validate():Boolean{
        var  valid = true
        val dobDayCheck = binding.dobEdtxtDay.text
        val dobMonthCheck = binding.dobEdtxtMonth.text
        val dobYearCheck = binding.dobEdtxtYear.text
        val dateInput="$dobDayCheck-$dobMonthCheck-$dobYearCheck"
        val date = SimpleDateFormat("dd-MM-yyyy", Locale.US).parse(dateInput)
        val currentDate = Calendar.getInstance().time
        if (dobDayCheck.isEmpty()||dobMonthCheck.isEmpty()||dobYearCheck.isEmpty()
            ||date<=currentDate){
            valid=false
        }
        return valid

    }


}