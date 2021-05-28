package com.example.javas.ui.symptoms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.javas.R
import com.example.javas.databinding.FragmentSymptomTwoBinding


class SymptomTwoFragment : Fragment() {

    private lateinit var _binding: FragmentSymptomTwoBinding
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSymptomTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val backPressed = SymptomTwoFragmentArgs.fromBundle(arguments as Bundle).backpressed
        val sakitPernapasan = SymptomTwoFragmentArgs.fromBundle(arguments as Bundle).sakitPernapasan
        val demam = SymptomTwoFragmentArgs.fromBundle(arguments as Bundle).demam
        val batukKering = SymptomTwoFragmentArgs.fromBundle(arguments as Bundle).batukKering
        val sakitTenggorokan = SymptomTwoFragmentArgs.fromBundle(arguments as Bundle).sakitTenggorokan
        val flu = SymptomTwoFragmentArgs.fromBundle(arguments as Bundle).sedangFlu
        val asma = SymptomTwoFragmentArgs.fromBundle(arguments as Bundle).asma
        val penyakitParu = SymptomTwoFragmentArgs.fromBundle(arguments as Bundle).penyakitParu
        val sakitKepala = SymptomTwoFragmentArgs.fromBundle(arguments as Bundle).sakitKepala
        val sakitJantung = SymptomTwoFragmentArgs.fromBundle(arguments as Bundle).sakitJantung
        val diabetes = SymptomTwoFragmentArgs.fromBundle(arguments as Bundle).diabetes

        if (backPressed){
            if (asma) {
                binding.rbYes1.isChecked = true
            } else {
                binding.rbNo1.isChecked = true
            }

            if (penyakitParu) {
                binding.rbYes2.isChecked = true
            } else {
                binding.rbYes2.isChecked = true
            }

            if (sakitKepala) {
                binding.rbYes3.isChecked = true
            } else {
                binding.rbNo3.isChecked = true
            }

            if (sakitJantung) {
                binding.rbYes4.isChecked = true
            } else {
                binding.rbNo4.isChecked = true
            }

            if (diabetes) {
                binding.rbYes5.isChecked = true
            } else {
                binding.rbNo5.isChecked = true
            }
        }

        binding.btnNextThree.setOnClickListener {
            val toSymptomsThree = SymptomTwoFragmentDirections.actionSymptomTwoFragmentToSymptomThreeFragment()
            toSymptomsThree.sakitPernapasan = sakitPernapasan
            toSymptomsThree.demam = demam
            toSymptomsThree.batukKering = batukKering
            toSymptomsThree.sakitTenggorokan = sakitTenggorokan
            toSymptomsThree.sedangFlu = flu
            toSymptomsThree.asma = binding.rgAsma.checkedRadioButtonId == R.id.rbYes1
            toSymptomsThree.penyakitParu = binding.rgParu.checkedRadioButtonId == R.id.rbYes2
            toSymptomsThree.sakitKepala = binding.rgKepala.checkedRadioButtonId == R.id.rbYes3
            toSymptomsThree.sakitJantung = binding.rgJantung.checkedRadioButtonId == R.id.rbYes4
            toSymptomsThree.diabetes = binding.rgDiabetes.checkedRadioButtonId == R.id.rbYes5


            if (validate()){
                view.findNavController()
                    .navigate(R.id.action_symptomTwoFragment_to_symptomThreeFragment)
            }else {
                Toast.makeText(context, "Mohon jawab semua pertanyaan", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun validate(): Boolean {
        var valid = true
        if (binding.rgAsma.checkedRadioButtonId == -1
            || binding.rgParu.checkedRadioButtonId == -1
            || binding.rgKepala.checkedRadioButtonId == -1
            || binding.rgJantung.checkedRadioButtonId == -1
            || binding.rgDiabetes.checkedRadioButtonId == -1) {

            valid = false
        }
        return valid
    }


}