package com.example.javas.ui.register

import androidx.lifecycle.ViewModel
import com.example.javas.data.UserRepository

class RegisterViewModel (
    private val repository: UserRepository
) : ViewModel() {

    fun register(email: String,password: String)= repository.register(email, password)

    fun createUser(email: String)=repository.createUser(email)

}