package com.example.firebasegoogleauth.presentation.sign_in

data class SignInResult(
    val data: UserData?,
    val errorMassage: String?
)

data class UserData(
    val userId: String,
    val username: String?,
    val profilePictureUrl: String?
)