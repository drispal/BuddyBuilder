package dj2al.example.buddybuilder.data.auth

import com.google.firebase.auth.FirebaseUser
import dj2al.example.buddybuilder.data.Resource

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser>
    fun logout()
}


