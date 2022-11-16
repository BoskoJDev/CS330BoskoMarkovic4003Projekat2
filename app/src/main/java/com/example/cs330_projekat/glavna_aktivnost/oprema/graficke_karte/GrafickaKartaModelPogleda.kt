package com.example.cs330_projekat.glavna_aktivnost.oprema.graficke_karte

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cs330_projekat.glavna_aktivnost.baza.Baza
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GrafickaKartaModelPogleda(aplikacija: Application) : AndroidViewModel(aplikacija)
{
    val sveGrafickaKarte: LiveData<List<GrafickaKarta>>
    val repozitorijum: GrafickaKartaRepozitorijum

    init
    {
        this.repozitorijum = GrafickaKartaRepozitorijum(Baza.getBaza(aplikacija).getGrafickaKartaDao())
        this.sveGrafickaKarte = this.repozitorijum.sveGrafickaKarte
    }

    fun ukloniGrafickuKartu(gk: GrafickaKarta)
    {
        viewModelScope.launch(Dispatchers.IO) { repozitorijum.ukloniGrafickuKartu(gk) }
    }
}