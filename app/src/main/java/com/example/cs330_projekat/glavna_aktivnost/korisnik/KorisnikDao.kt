package com.example.cs330_projekat.glavna_aktivnost.korisnik

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cs330_projekat.glavna_aktivnost.korisnik.Korisnik

@Dao
interface KorisnikDao
{
    @Insert
    suspend fun dodajKorisnika(korisnik: Korisnik)

    @Query(value = "SELECT * FROM korisnici")
    fun citajSveKorisnike(): LiveData<List<Korisnik>>
}