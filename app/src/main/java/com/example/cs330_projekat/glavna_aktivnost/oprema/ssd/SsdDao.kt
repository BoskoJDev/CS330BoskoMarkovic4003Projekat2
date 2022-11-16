package com.example.cs330_projekat.glavna_aktivnost.oprema.ssd

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query

@Dao
interface SsdDao
{
    @Delete
    suspend fun ukloniSsd(ssd: Ssd)

    @Query("select * from ssd_ovi")
    fun getSviSsdovi(): LiveData<List<Ssd>>
}