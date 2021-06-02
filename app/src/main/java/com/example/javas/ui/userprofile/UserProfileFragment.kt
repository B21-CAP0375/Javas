package com.example.javas.ui.userprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.javas.databinding.FragmentLoginBinding
import com.example.javas.databinding.FragmentUserProfileBinding
import com.example.javas.ui.homepage.HomePageFragmentArgs
import com.example.javas.ui.homepage.HomePageFragmentDirections
import com.example.javas.ui.login.LoginViewModel
import com.example.javas.utils.ViewModelFactory


class UserProfileFragment : Fragment() {

    private lateinit var  _binding: FragmentUserProfileBinding
    private val binding get() = _binding
    private lateinit var  viewModel: UserProfileViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[UserProfileViewModel::class.java]

        val name = UserProfileFragmentArgs.fromBundle(arguments as Bundle).name

        var checkPassword= "true"
        viewModel.getUser(name).get().addOnSuccessListener {
            document->
            binding.phoneEdtxt.setText(document.getString("phone"))
            binding.alamatEdtxt.setText(document.getString("alamat"))
            checkPassword = document.getString("password").toString()
        }


        binding.btnContinueLoginPage.setOnClickListener {
            val phone = binding.phoneEdtxt.text.toString()
            val alamat = binding.alamatEdtxt.text.toString()
            val curPassword = binding.currentPasswordEdtxt.text.toString()
            val password = binding.passwordEdtxt.text.toString()
            viewModel.updatePhone(name,phone)
            viewModel.updateAlamat(name,alamat)
            var berhasil = "update berhasil"
            if (checkPassword==curPassword){
                viewModel.updatePasswordFire(name,password)
                viewModel.updatePassword(password)
                berhasil= "update berhasil password diganti"
            }
            Toast.makeText(context, berhasil, Toast.LENGTH_SHORT).show()
        }
        binding.btnBackRegisterPage.setOnClickListener {
            val toHomePage = UserProfileFragmentDirections.actionUserProfileFragmentToHomePageFragment()
            toHomePage.name = name
            view.findNavController().navigate(toHomePage)
        }
        binding.btnLogoutRegisterPage.setOnClickListener {
            viewModel.logOut()
            val toLandingPage = UserProfileFragmentDirections.actionUserProfileFragmentToLandingPageFragment()
            view.findNavController().navigate(toLandingPage)
        }
    }


}