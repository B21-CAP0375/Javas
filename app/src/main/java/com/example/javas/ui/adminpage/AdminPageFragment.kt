package com.example.javas.ui.adminpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.javas.R
import com.example.javas.databinding.FragmentAdminPageBinding
import com.example.javas.databinding.FragmentLoginBinding
import com.example.javas.ui.login.LoginViewModel
import com.example.javas.utils.ViewModelFactory


class AdminPageFragment : Fragment() {

    private lateinit var  _binding: FragmentAdminPageBinding
    private val binding get() = _binding
    private lateinit var  viewModel: AdminPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAdminPageBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[AdminPageViewModel::class.java]

        binding.btnLoginLoginPage.setOnClickListener {
            activity?.let {
                val docRef = viewModel.getUser(binding.emailEdtxt.text.toString())

                docRef.get().addOnSuccessListener {
                    document->
                    if (document.getString("role").toString()=="admin"){
                        Toast.makeText(context, document.getString("hospital"), Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(context, "Bukan Admin", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


}