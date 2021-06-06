package com.example.javas.ui.resultnegativepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.javas.R
import com.example.javas.databinding.FragmentResultNegativeBinding
import com.example.javas.databinding.FragmentSymptomFourBinding
import com.example.javas.ui.symptoms.SymptomFourFragmentArgs


class ResultNegativeFragment : Fragment() {


    private lateinit var _binding: FragmentResultNegativeBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentResultNegativeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.btnBackPositivePage.setOnClickListener {
            val name = ResultNegativeFragmentArgs.fromBundle(arguments as Bundle).name
            val toChooseDate = ResultNegativeFragmentDirections.actionResultNegativeFragmentToChooseDateVaccineFragment()
            toChooseDate.name= name
            view.findNavController().navigate(toChooseDate)
        }
    }


}