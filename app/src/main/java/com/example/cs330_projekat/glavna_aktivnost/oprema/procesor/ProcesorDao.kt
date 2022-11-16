package com.example.cs330_projekat.glavna_aktivnost.oprema.procesor

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query

@Dao
interface ProcesorDao
{
    @Delete
    suspend fun ukloniProcesor(p: Procesor)

    @Query("select * from procesori")
    fun getSveProcesore(): LiveData<List<Procesor>>
}