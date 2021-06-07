package com.example.javas.ui.datepage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.javas.R
import com.example.javas.databinding.FragmentDatePageBinding
import com.example.javas.databinding.FragmentUserProfileBinding
import com.example.javas.ui.adminlistdate.AdminListDateViewModel
import com.example.javas.ui.choosedatevaccine.ChooseDateVaccineFragmentDirections
import com.example.javas.ui.userprofile.UserProfileViewModel
import com.example.javas.utils.ViewModelFactory


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
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[DatePageViewModel::class.java]

        val name = DatePageFragmentArgs.fromBundle(arguments as Bundle).name

        val dateVaccine = viewModel.getVaccineDate(name)


        dateVaccine
            .get()
            .addOnSuccessListener {
                document ->
                val vaksindate =document.getString("vaccineDate").toString()
                val hospital =document.getString("hospital").toString()
                checkNull(vaksindate, hospital)
            }

        binding.btnBatalVaksin.setOnClickListener {
            viewModel.deleteVaccine(name)
                .addOnSuccessListener {
                    Toast.makeText(context, "Anda batal vaksinasi", Toast.LENGTH_SHORT).show()
                    Log.d("DatePageFragment", "Delete vaksinasi")
                }
            val toHomePage = DatePageFragmentDirections.actionDatePageFragmentToHomePageFragment2()
            toHomePage.name=name
            view.findNavController().navigate(toHomePage)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    fun checkNull(vaksindate: String, hospital: String) {
        if (vaksindate == "null" && hospital == "null"){
            binding.dateEdt.visibility = View.INVISIBLE
            binding.hospitalEdt.visibility = View.INVISIBLE
            binding.imageView.visibility = View.INVISIBLE
            binding.imageView2.visibility = View.INVISIBLE
            binding.datePageTitle.setText("Anda Belum Terdaftar")
            binding.btnBatalVaksin.visibility = View.INVISIBLE
        } else{
            binding.dateEdt.text = vaksindate
            binding.hospitalEdt.text = hospital
        }

    }
}