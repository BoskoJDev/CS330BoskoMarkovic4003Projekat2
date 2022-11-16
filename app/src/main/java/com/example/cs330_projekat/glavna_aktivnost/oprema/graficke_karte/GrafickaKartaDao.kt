package com.example.cs330_projekat.glavna_aktivnost.oprema.graficke_karte

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query

@Dao
interface GrafickaKartaDao
{
    @Delete
    suspend fun ukloniGrafickuKartu(gk: GrafickaKarta)

    @Query("select * from graficke_karte")
    fun getSveGrafickeKarte(): LiveData<List<GrafickaKarta>>
}