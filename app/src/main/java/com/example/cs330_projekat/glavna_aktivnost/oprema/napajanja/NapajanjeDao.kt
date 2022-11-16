package com.example.cs330_projekat.glavna_aktivnost.oprema.napajanja

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query

@Dao
interface NapajanjeDao
{
    @Delete
    suspend fun ukloniNapajanje(n: Napajanje)

    @Query("select * from napajanja")
    fun getSvaNapajanja() : LiveData<List<Napajanje>>
}