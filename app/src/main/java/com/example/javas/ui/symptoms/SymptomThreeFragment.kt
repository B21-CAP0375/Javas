package com.example.javas.ui.symptoms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.javas.R
import com.example.javas.databinding.FragmentSymptomThreeBinding


class SymptomThreeFragment : Fragment() {

    private lateinit var _binding: FragmentSymptomThreeBinding
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSymptomThreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val backPressed = SymptomThreeFragmentArgs.fromBundle(arguments as Bundle).backpressed
        val sakitPernapasan = SymptomThreeFragmentArgs.fromBundle(arguments as Bundle).sakitPernapasan
        val demam = SymptomThreeFragmentArgs.fromBundle(arguments as Bundle).demam
        val batukKering = SymptomThreeFragmentArgs.fromBundle(arguments as Bundle).batukKering
        val sakitTenggorokan = SymptomThreeFragmentArgs.fromBundle(arguments as Bundle).sakitTenggorokan
        val flu = SymptomThreeFragmentArgs.fromBundle(arguments as Bundle).sedangFlu
        val asma = SymptomThreeFragmentArgs.fromBundle(arguments as Bundle).asma
        val penyakitParu = SymptomThreeFragmentArgs.fromBundle(arguments as Bundle).penyakitParu
        val sakitKepala = SymptomThreeFragmentArgs.fromBundle(arguments as Bundle).sakitKepala
        val sakitJantung = SymptomThreeFragmentArgs.fromBundle(arguments as Bundle).sakitJantung
        val diabetes = SymptomThreeFragmentArgs.fromBundle(arguments as Bundle).diabetes
        val hiperTensi = SymptomThreeFragmentArgs.fromBundle(arguments as Bundle).hiperTensi
        val kecapean = SymptomThreeFragmentArgs.fromBundle(arguments as Bundle).kecapean
        val gangguanPencernaan = SymptomThreeFragmentArgs.fromBundle(arguments as Bundle).gangguanPencernaan
        val baruBepergian = SymptomThreeFragmentArgs.fromBundle(arguments as Bundle).baruBepergian
        val bertemuPasienCovid = SymptomThreeFragmentArgs.fromBundle(arguments as Bundle).bertemuPasienCovid

        if (backPressed){
            if (hiperTensi) {
                binding.rbYes1.isChecked = true
            } else {
                binding.rbNo1.isChecked = true
            }

            if (kecapean) {
                binding.rbYes2.isChecked = true
            } else {
                binding.rbYes2.isChecked = true
            }

            if (gangguanPencernaan) {
                binding.rbYes3.isChecked = true
            } else {
                binding.rbNo3.isChecked = true
            }

            if (baruBepergian) {
                binding.rbYes4.isChecked = true
            } else {
                binding.rbNo4.isChecked = true
            }

            if (bertemuPasienCovid) {
                binding.rbYes5.isChecked = true
            } else {
                binding.rbNo5.isChecked = true
            }
        }
        binding.btnNextFour.setOnClickListener {
            val toSymptomsFour = SymptomThreeFragmentDirections.actionSymptomThreeFragmentToSymptomFourFragment()
            toSymptomsFour.sakitPernapasan = sakitPernapasan
            toSymptomsFour.demam = demam
            toSymptomsFour.batukKering = batukKering
            toSymptomsFour.sakitTenggorokan = sakitTenggorokan
            toSymptomsFour.sedangFlu = flu
            toSymptomsFour.asma = asma
            toSymptomsFour.penyakitParu = penyakitParu
            toSymptomsFour.sakitKepala = sakitKepala
            toSymptomsFour.sakitJantung = sakitJantung
            toSymptomsFour.diabetes = diabetes
            toSymptomsFour.hiperTensi = binding.rgHiper.checkedRadioButtonId == R.id.rbYes1
            toSymptomsFour.kecapean = binding.rgKecapean.checkedRadioButtonId == R.id.rbYes2
            toSymptomsFour.gangguanPencernaan = binding.rgGangguan.checkedRadioButtonId == R.id.rbYes3
            toSymptomsFour.baruBepergian = binding.rgTravel.checkedRadioButtonId == R.id.rbYes4
            toSymptomsFour.bertemuPasienCovid = binding.rgBertemu.checkedRadioButtonId == R.id.rbYes5

            if (validate()){
                view.findNavController()
                    .navigate(R.id.action_symptomThreeFragment_to_symptomFourFragment)
            }else {
                Toast.makeText(context, "Mohon jawab semua pertanyaan", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun validate(): Boolean {
        var valid = true
        if (binding.rgHiper.checkedRadioButtonId == -1
            || binding.rgKecapean.checkedRadioButtonId == -1
            || binding.rgGangguan.checkedRadioButtonId == -1
            || binding.rgTravel.checkedRadioButtonId == -1
            || binding.rgBertemu.checkedRadioButtonId == -1) {

            valid = false
        }
        return valid
    }


}