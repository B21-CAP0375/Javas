package com.example.javas.ui.choosedatevaccine

import androidx.lifecycle.ViewModel
import com.example.javas.data.UserRepository

class ChooseDateVaccineViewModel (
    private val repository: UserRepository
) : ViewModel() {

    fun  getDate(hospital:String) = repository.getDate(hospital)

    fun getUser(email:String)= repository.getUser(email)

    fun reduceUser(hospital: String,date:String)=repository.reducePerson(hospital, date)

    fun setHospital(hospital: String,date: String)=repository.setHospital(hospital, date)
}