package com.example.cs330_projekat.glavna_aktivnost.oprema.graficke_karte

class GrafickaKartaRepozitorijum(private val dao: GrafickaKartaDao)
{
    val sveGrafickaKarte = this.dao.getSveGrafickeKarte()

    suspend fun ukloniGrafickuKartu(gk: GrafickaKarta) = this.dao.ukloniGrafickuKartu(gk)
}