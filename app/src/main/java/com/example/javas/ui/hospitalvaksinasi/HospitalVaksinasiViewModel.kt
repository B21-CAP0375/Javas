package com.example.javas.ui.hospitalvaksinasi

import androidx.lifecycle.ViewModel
import com.example.javas.data.UserRepository

class HospitalVaksinasiViewModel (
    private val repository: UserRepository
) : ViewModel() {
    fun setHospital(hospital:String,date:String)= repository.setHospital(hospital, date)
}