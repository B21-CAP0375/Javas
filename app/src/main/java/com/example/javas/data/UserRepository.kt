package com.example.javas.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository (
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore
){

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(
            firebaseAuth: FirebaseAuth,
            firestore: FirebaseFirestore
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(firebaseAuth,firestore)
            }
    }
    fun login(email: String, password: String)=firebaseAuth.signInWithEmailAndPassword(email, password)


    fun register(email: String, password: String) = firebaseAuth.createUserWithEmailAndPassword(email, password)

    fun createUser(email: String)= firestore.collection("users").document(email)

    fun getUser(email: String)= firestore.collection("users").document(email)

    fun currentUser() = firebaseAuth.currentUser

    fun logout() = firebaseAuth.signOut()
}