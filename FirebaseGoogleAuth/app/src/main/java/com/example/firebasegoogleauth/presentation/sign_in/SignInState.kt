package com.example.firebasegoogleauth.presentation.sign_in

data class SignInState(
    val isSignInSiSuccessful: Boolean = false,
    val signInError: String? = null
)
