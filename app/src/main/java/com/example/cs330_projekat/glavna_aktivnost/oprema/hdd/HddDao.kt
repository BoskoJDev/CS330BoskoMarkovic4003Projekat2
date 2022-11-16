package com.example.cs330_projekat.glavna_aktivnost.oprema.hdd

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query

@Dao
interface HddDao
{
    @Delete
    suspend fun ukloniHdd(hdd: Hdd)

    @Query("select * from hdd_ovi")
    fun getSveHdd(): LiveData<List<Hdd>>
}