package com.example.cs330_projekat.glavna_aktivnost.korisnik

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cs330_projekat.glavna_aktivnost.baza.Baza
import com.example.cs330_projekat.glavna_aktivnost.korisnik.Korisnik
import com.example.cs330_projekat.glavna_aktivnost.korisnik.KorisnikRepozitorijum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KorisnikModelPogleda(aplikacija: Application) : AndroidViewModel(aplikacija)
{
    val sviKorisnici: LiveData<List<Korisnik>>
    val repozitorijum: KorisnikRepozitorijum

    init
    {
        this.repozitorijum = KorisnikRepozitorijum(Baza.getBaza(aplikacija).getKorisnikDao())
        this.sviKorisnici = this.repozitorijum.sviKorisnici
    }

    fun dodajKorisnika(korisnik: Korisnik)
    {
        viewModelScope.launch(Dispatchers.IO) { repozitorijum.dodajKorisnika(korisnik) }
    }
}