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

        viewModel.getUser(name)
            .get()
            .addOnSuccessListener {
                document->
                binding.userNIK.text="NIK : ${document.getString("nik")}"
                binding.userName.text="Nama : ${document.getString("name")}"
                binding.userGender.text="Jenis Kelamin : ${document.getString("gender")}"
                binding.userDob.text="Tanggal Lahir : ${document.getString("dob")}"
                binding.userBirthplace.text="Tempat Lahir : ${document.getString("place")}"
                binding.userPhone.text="No Handphone : ${document.getString("phone")}"
                binding.userAlamat.text="Alamat : ${document.getString("alamat")}"
            }



        binding.btnContinueLoginPage.setOnClickListener {
            val toChangeUser = UserProfileFragmentDirections.actionUserProfileFragmentToUpdateUserFragment()
            toChangeUser.name=name
            view.findNavController().navigate(toChangeUser)
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