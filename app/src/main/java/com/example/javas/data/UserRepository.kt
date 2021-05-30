package com.example.javas.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

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

    //loginpage
    fun login(email: String, password: String)=firebaseAuth.signInWithEmailAndPassword(email, password)
    fun getUser(email: String)= firestore.collection("users").document(email)

    //registerpage
    fun register(email: String, password: String) = firebaseAuth.createUserWithEmailAndPassword(email, password)

    fun createUser(email: String)= firestore.collection("users").document(email)




    //user profile page
    fun updatePassword(newPassWord: String) = firebaseAuth.currentUser?.updatePassword(newPassWord)

    fun checkPassword(email: String,password: String)=firestore.collection("users").document(email)

    fun updatePasswordFire(email: String , newPassWord:String) = firestore.collection("users").document(email).update("password", newPassWord)
    fun updatePhone(email: String , phone:String) = firestore.collection("users").document(email).update("phone", phone)
    fun updateAlamat(email: String , alamat:String) = firestore.collection("users").document(email).update("alamat", alamat)


    fun getVaccineDate(email: String)=firestore.collection("users").document(email).collection("vaccination").document("vaccineDate")


    //admin vaksinasi time
    fun setHospital(hospital:String, date:String) = firestore.collection(hospital).document(date)
    fun getHospital(hospital: String)=firestore.collection(hospital)


    fun getDate(hospital: String)=firestore.collection(hospital).whereEqualTo("status", true).get()

    fun currentUser() = firebaseAuth.currentUser

    fun logout() = firebaseAuth.signOut()
}