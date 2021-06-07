package com.example.javas.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.javas.R
import com.example.javas.databinding.FragmentLandingPageBinding
import com.example.javas.databinding.FragmentLoginBinding
import com.example.javas.utils.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {


    private lateinit var  _binding: FragmentLoginBinding
    private val binding get() = _binding
    private lateinit var  viewModel:LoginViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]


        binding.btnLoginLoginPage.setOnClickListener {
            if(validate()){
                activity?.let {
                    val toHomePage = LoginFragmentDirections.actionLoginFragmentToHomePageFragment()
                    val docRef = viewModel.getUser(binding.emailEdtxt.text.toString())
                    binding.progressBarDetails.visibility = View.VISIBLE

                    viewModel.login(binding.emailEdtxt.text.toString(),binding.passwordEdtxt.text.toString())
                        .addOnCompleteListener(it) { task ->
                            if (task.isSuccessful) {
                                docRef.get()
                                    .addOnSuccessListener { document ->
                                        if (document != null) {
                                            toHomePage.name= document.getString("email").toString()
                                            binding.progressBarDetails.visibility = View.GONE
                                            view.findNavController().navigate(toHomePage)
                                        }
                                    }
                            }
                            else{
                                binding.progressBarDetails.visibility = View.GONE
                                Toast.makeText(context, "Email atau Password salah", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }
        }
        binding.btnRegisterLoginPage.setOnClickListener{
            view.findNavController().navigate(R.id.action_loginFragment_to_registerOneFragment)
        }
    }

    private fun validate():Boolean{
        var  valid = true
        val emailCheck = binding.emailEdtxt.text
        val passCheck = binding.passwordEdtxt.text

        //passcheck dan emailcheck mendapatkan data dari api
        if (emailCheck.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(emailCheck).matches()) {
            valid=false
            binding.emailEdtxt.error = "Email Kosong"
        }
        if (passCheck.isEmpty() ){
            valid=false
            binding.passwordEdtxt.error = "Password Kosong"
        }
        return valid
    }

}