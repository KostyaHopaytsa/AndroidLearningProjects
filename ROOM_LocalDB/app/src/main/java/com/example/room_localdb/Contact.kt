package com.example.room_localdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    val firsName: String,
    val lastName: String,
    val phoneNumber: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
