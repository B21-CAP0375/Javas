package com.example.javas.ui.symptom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNextThree.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_symptomTwoFragment_to_symptomThreeFragment)
        }

    }


}