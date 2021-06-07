package com.example.javas.ui.resultpositivepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.javas.R
import com.example.javas.databinding.FragmentResultPositiveBinding
import com.example.javas.databinding.FragmentSymptomFourBinding


class ResultPositiveFragment : Fragment() {

    private lateinit var _binding: FragmentResultPositiveBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentResultPositiveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnBackPositivePage.setOnClickListener {
            val name = ResultPositiveFragmentArgs.fromBundle(arguments as Bundle).name
            val toHomePage =ResultPositiveFragmentDirections.actionResultPositiveFragmentToHomePageFragment()
            toHomePage.name = name
            view.findNavController().navigate(toHomePage)
        }
    }


}