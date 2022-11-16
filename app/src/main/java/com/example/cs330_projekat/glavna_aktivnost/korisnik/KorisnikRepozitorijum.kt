package com.example.cs330_projekat.glavna_aktivnost.korisnik

import androidx.lifecycle.LiveData

class KorisnikRepozitorijum(private val korisnikDao: KorisnikDao)
{
    val sviKorisnici: LiveData<List<Korisnik>> = this.korisnikDao.citajSveKorisnike()

    suspend fun dodajKorisnika(korisnik: Korisnik) = this.korisnikDao.dodajKorisnika(korisnik)
}