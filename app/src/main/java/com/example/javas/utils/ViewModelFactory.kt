package com.example.javas.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.javas.data.UserRepository
import com.example.javas.ui.adminpage.AdminPageViewModel
import com.example.javas.ui.datepage.DatePageViewModel
import com.example.javas.ui.hospitalvaksinasi.HospitalVaksinasiViewModel
import com.example.javas.ui.login.LoginViewModel
import com.example.javas.ui.register.RegisterViewModel
import com.example.javas.ui.userprofile.UserProfileViewModel

class ViewModelFactory private constructor(private val tourismRepository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance
                ?: synchronized(this) {
                    instance
                        ?: ViewModelFactory(
                            Injection.provideRepository(
                                context
                            )
                        )
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(tourismRepository) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(tourismRepository) as T
            }
            modelClass.isAssignableFrom(UserProfileViewModel::class.java) -> {
                UserProfileViewModel(tourismRepository) as T
            }
            modelClass.isAssignableFrom(DatePageViewModel::class.java) -> {
                DatePageViewModel(tourismRepository) as T
            }
            modelClass.isAssignableFrom(AdminPageViewModel::class.java) -> {
                AdminPageViewModel(tourismRepository) as T
            }
            modelClass.isAssignableFrom(HospitalVaksinasiViewModel::class.java) -> {
                HospitalVaksinasiViewModel(tourismRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}