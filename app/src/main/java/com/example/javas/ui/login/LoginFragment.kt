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
import com.example.javas.ui.register.RegisterThreeFragmentDirections
import com.example.javas.utils.ViewModelFactory


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
            activity?.let {
                val toHomePage = LoginFragmentDirections.actionLoginFragmentToHomePageFragment()
                val docRef = viewModel.getUser(binding.emailEdtxt.text.toString())
                docRef.get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            toHomePage.name= document.getString("email").toString()
                            Toast.makeText(context, document.getString("email"), Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "null", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(context, "failure", Toast.LENGTH_SHORT).show()
                    }

                viewModel.login(binding.emailEdtxt.text.toString(),binding.passwordEdtxt.text.toString())
                    .addOnCompleteListener(it) { task ->
                        if (task.isSuccessful) {
                            view.findNavController().navigate(toHomePage)
                            // If sign in fails, display a message to the user.
                        } else{
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
        }
        if (passCheck.isEmpty() || passCheck.length < 6 || passCheck.length > 10){
            valid=false
        }
        return valid
    }

}