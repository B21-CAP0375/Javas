package com.example.javas.utils

import android.content.Context
import com.example.javas.data.UserRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val firebaseAuth = Firebase.auth
        val firestore= FirebaseFirestore.getInstance()

        return UserRepository.getInstance(firebaseAuth,firestore)
    }
}