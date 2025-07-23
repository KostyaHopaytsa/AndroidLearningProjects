package com.example.room_localdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert

@Dao
interface ContactDao {

    @Upsert
    suspend fun upsertContacts(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)
}