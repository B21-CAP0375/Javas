package com.example.javas.ui.homepage

import androidx.lifecycle.ViewModel
import com.example.javas.data.UserRepository

class HomePageViewModel (
    private val repository: UserRepository
) : ViewModel() {

    fun getVaccineDate(email:String)= repository.getVaccineDate(email)
}