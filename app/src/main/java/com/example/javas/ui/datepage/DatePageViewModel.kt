package com.example.javas.ui.datepage

import androidx.lifecycle.ViewModel
import com.example.javas.data.UserRepository

class DatePageViewModel(
    private val repository: UserRepository
) : ViewModel() {
    fun getVaccineDate(email:String) = repository.getVaccineDate(email)

    fun deleteVaccine(email: String) = repository.deleteVaccine(email)
}