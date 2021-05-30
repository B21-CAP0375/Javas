package com.example.javas.ui.datepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.javas.R
import com.example.javas.databinding.FragmentDatePageBinding
import com.example.javas.databinding.FragmentUserProfileBinding
import com.example.javas.ui.userprofile.UserProfileViewModel


class DatePageFragment : Fragment() {



    private lateinit var  _binding: FragmentDatePageBinding
    private val binding get() = _binding
    private lateinit var  viewModel: DatePageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDatePageBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}