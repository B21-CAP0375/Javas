package com.example.javas.ui.symptoms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.javas.R
import com.example.javas.databinding.FragmentSymptomOneBinding


class SymptomOneFragment : Fragment() {

    private lateinit var _binding: FragmentSymptomOneBinding
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSymptomOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val backPressed = SymptomOneFragmentArgs.fromBundle(arguments as Bundle).backpressed
        if (backPressed) {
            val sakitPernapasan =
                SymptomOneFragmentArgs.fromBundle(arguments as Bundle).sakitPernapasan
            val demam = SymptomOneFragmentArgs.fromBundle(arguments as Bundle).demam
            val batukKering = SymptomOneFragmentArgs.fromBundle(arguments as Bundle).batukKering
            val sakitTenggorokan =
                SymptomOneFragmentArgs.fromBundle(arguments as Bundle).sakitTenggorokan
            val flu = SymptomOneFragmentArgs.fromBundle(arguments as Bundle).sedangFlu

            if (sakitPernapasan) {
                binding.rbYesSp.isChecked = true
            } else {
                binding.rbNoSp.isChecked = true
            }

            if (demam) {
                binding.rbYesD.isChecked = true
            } else {
                binding.rbYesD.isChecked = true
            }

            if (batukKering) {
                binding.rbYesBk.isChecked = true
            } else {
                binding.rbNoBk.isChecked = true
            }

            if (sakitTenggorokan) {
                binding.rbYesSt.isChecked = true
            } else {
                binding.rbNoSt.isChecked = true
            }

            if (flu) {
                binding.rbYesFlu.isChecked = true
            } else {
                binding.rbNoFlu.isChecked = true
            }

        }

        binding.btnNextTwo.setOnClickListener {
            val name=SymptomOneFragmentArgs.fromBundle(arguments as Bundle).name
            val toSymptomsTwo = SymptomOneFragmentDirections.actionSymptomOneFragmentToSymptomTwoFragment()
            toSymptomsTwo.name= name
            toSymptomsTwo.sakitPernapasan = binding.rgSp.checkedRadioButtonId == R.id.rbYesSp
            toSymptomsTwo.demam = binding.rgDemam.checkedRadioButtonId == R.id.rbYesD
            toSymptomsTwo.batukKering = binding.rgBk.checkedRadioButtonId == R.id.rbYesBk
            toSymptomsTwo.sakitTenggorokan = binding.rgSt.checkedRadioButtonId == R.id.rbYesSt
            toSymptomsTwo.sedangFlu = binding.rgFlu.checkedRadioButtonId == R.id.rbYesFlu

            if (validate()) {
                view.findNavController()
                    .navigate(toSymptomsTwo)
            } else {
                Toast.makeText(context, "Mohon jawab semua pertanyaan", Toast.LENGTH_SHORT).show()
            }


        }
    }

    private fun validate(): Boolean {
        var valid = true
        if (binding.rgSp.checkedRadioButtonId == -1
            || binding.rgDemam.checkedRadioButtonId == -1
            || binding.rgBk.checkedRadioButtonId == -1
            || binding.rgSt.checkedRadioButtonId == -1
            || binding.rgFlu.checkedRadioButtonId == -1) {

            valid = false
        }
        return valid
    }


}