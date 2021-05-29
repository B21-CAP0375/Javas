package com.example.javas.ui.adminpage

import androidx.lifecycle.ViewModel
import com.example.javas.data.UserRepository

class AdminPageViewModel (
    private val repository: UserRepository
) : ViewModel() {

    fun getUser(email:String) = repository.getUser(email)
    fun login(email: String,password: String)= repository.login(email, password)

}