package com.example.javas.ui.symptoms

import androidx.lifecycle.ViewModel
import com.example.javas.data.UserRepository

class SymptomViewModel (
    private val repository: UserRepository
) : ViewModel() {
    fun getUser(email:String)= repository.getUser(email)

}