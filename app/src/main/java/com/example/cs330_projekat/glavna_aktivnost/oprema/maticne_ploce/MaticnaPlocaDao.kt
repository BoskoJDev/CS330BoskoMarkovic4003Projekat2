package com.example.cs330_projekat.glavna_aktivnost.oprema.maticne_ploce

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query

@Dao
interface MaticnaPlocaDao
{
    @Delete
    suspend fun ukloniMaticnuPlocu(maticnaPloca: MaticnaPloca)

    @Query("select * from maticne_ploce")
    fun getSveMaticnePloce(): LiveData<List<MaticnaPloca>>
}