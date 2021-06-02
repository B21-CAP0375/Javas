package com.example.javas.ui.userprofile

import androidx.lifecycle.ViewModel
import com.example.javas.data.UserRepository

class UserProfileViewModel (
    private val repository: UserRepository
) : ViewModel() {

    fun checkPassword(email: String) = repository.checkPassword(email)

    fun updatePassword(newPassword: String) = repository.updatePassword(newPassword)

    fun updatePhone(email: String,phone:String) = repository.updatePhone(email, phone)

    fun updateAlamat(email: String,alamat: String)= repository.updateAlamat(email, alamat)

    fun updatePasswordFire(email: String,newPassword: String)=repository.updatePasswordFire(email,newPassword)

    fun getUser(email: String)=repository.getUser(email)

    fun logOut()= repository.logout()


}