package com.example.javas.ui.login

import androidx.lifecycle.ViewModel
import com.example.javas.data.UserRepository

class LoginViewModel(
    private val repository: UserRepository
) : ViewModel() {

    fun login(email: String,password: String)= repository.login(email, password)

    fun getUser(email: String)=repository.getUser(email)
}