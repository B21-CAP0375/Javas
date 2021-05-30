package com.example.javas.ui.symptoms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.javas.R
import com.example.javas.databinding.FragmentSymptomFourBinding


class SymptomFourFragment : Fragment() {

    private lateinit var _binding: FragmentSymptomFourBinding
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSymptomFourBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val backPressed = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).backpressed
        val sakitPernapasan = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).sakitPernapasan
        val demam = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).demam
        val batukKering = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).batukKering
        val sakitTenggorokan = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).sakitTenggorokan
        val flu = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).sedangFlu
        val asma = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).asma
        val penyakitParu = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).penyakitParu
        val sakitKepala = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).sakitKepala
        val sakitJantung = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).sakitJantung
        val diabetes = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).diabetes
        val hiperTensi = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).hiperTensi
        val kecapean = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).kecapean
        val gangguanPencernaan = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).gangguanPencernaan
        val baruBepergian = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).baruBepergian
        val bertemuPasienCovid = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).bertemuPasienCovid
        val mengikutiAcara = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).mengikutiAcaraBesar
        val bertemuOrangBanyak = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).bertemuOrangBanyak
        val anggotaKeluargaBekerja = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).anggotaKeluargaBekerja
        val memakaiMasker = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).memakaiMasker
        val menggunakanSanitizer = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).menggunakanSanitizer

        if (backPressed){
            if (mengikutiAcara) {
                binding.rbYes1.isChecked = true
            } else {
                binding.rbNo1.isChecked = true
            }

            if (bertemuOrangBanyak) {
                binding.rbYes2.isChecked = true
            } else {
                binding.rbYes2.isChecked = true
            }

            if (anggotaKeluargaBekerja) {
                binding.rbYes3.isChecked = true
            } else {
                binding.rbNo3.isChecked = true
            }

            if (memakaiMasker) {
                binding.rbYes4.isChecked = true
            } else {
                binding.rbNo4.isChecked = true
            }

            if (menggunakanSanitizer) {
                binding.rbYes5.isChecked = true
            } else {
                binding.rbNo5.isChecked = true
            }
        }

        binding.btnNextResult.setOnClickListener {
            val name = SymptomFourFragmentArgs.fromBundle(arguments as Bundle).name
            val toResult = SymptomFourFragmentDirections.actionSymptomFourFragmentToChooseDateVaccineFragment()
            toResult.name=name
            view.findNavController().navigate(toResult)
        }

    }


}