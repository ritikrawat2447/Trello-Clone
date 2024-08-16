package com.example.trelloclone.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trelloclone.Models.USER
import com.example.trelloclone.Models.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AuthViewModel : ViewModel() {

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableLiveData<AuthState>()
    val authState : LiveData<AuthState> = _authState

    val userData = mutableStateOf<UserData?>(null)

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus(){
        if(auth.currentUser==null){
            _authState.value = AuthState.Unauthenticated
        }else{
            _authState.value = AuthState.Authenticated
        }
    }

    fun signIn(email : String , password : String ){
        if ( email.isEmpty() || password.isEmpty() ){
            _authState.value = AuthState.Error("Email or Password can't be Empty!!")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{ login ->
                if ( login.isSuccessful ){
                    _authState.value = AuthState.Authenticated
                }else{
                    _authState.value =
                        AuthState.Error(login.exception?.message ?: "Something Went Wrong")
                }

            }
    }

    fun signUp(email : String , password : String ){
        if ( email.isEmpty() || password.isEmpty() ){
            _authState.value = AuthState.Error("Email or Password can't be Empty!!")
            return
        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{ login ->
                if ( login.isSuccessful ){
                    _authState.value = AuthState.Authenticated
                }else{
                    _authState.value =
                        AuthState.Error(login.exception?.message ?: "Something Went Wrong")
                }

            }
    }

    fun signOut(){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }

    fun createOrUpdateProfile(
        name: String,
        email: String,
        imageUrl: String
    ) {
        var uid = auth.currentUser?.uid
        val userData = UserData(
                userId = uid!!,
                name = name,
                email = email,
                imageUrl = imageUrl
            )
        uid?.let {
            _authState.value = AuthState.Loading
            Firebase.firestore.collection(USER).document(uid).get()
                .addOnSuccessListener {
                    if (it.exists()) {
                        db.collection(USER).document(uid).set(userData)
                        inProcess.value = false
                        getUserData(uid)
                    } else {
                        db.collection(USER).document(uid).set(userData)
                        inProcess.value = false
                        getUserData(uid)
                    }
                }
                .addOnFailureListener {
                    handleException(it, "Cannot Retrieve User")
                }
        }

    }
}

sealed class AuthState{
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error( val message : String ) : AuthState()
}