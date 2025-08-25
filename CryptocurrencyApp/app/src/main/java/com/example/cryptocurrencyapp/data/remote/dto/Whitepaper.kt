package com.example.cryptocurrencyapp.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Whitepaper(
    val link: String,
    val thumbnail: String
)