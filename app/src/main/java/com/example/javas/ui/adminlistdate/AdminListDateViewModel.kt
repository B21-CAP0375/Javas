package com.example.javas.ui.adminlistdate

import androidx.lifecycle.ViewModel
import com.example.javas.data.UserRepository

class AdminListDateViewModel (
    private val repository: UserRepository
) : ViewModel() {
    fun getUser(hospital:String)= repository.getHospital(hospital)
}