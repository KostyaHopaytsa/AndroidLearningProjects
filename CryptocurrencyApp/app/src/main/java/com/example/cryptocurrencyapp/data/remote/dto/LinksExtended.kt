package com.example.cryptocurrencyapp.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinksExtended(
    val stats: Stats,
    val type: String,
    val url: String
)