package com.example.cs330_projekat.glavna_aktivnost.oprema.ssd

class SsdRepozitorijum(private val dao: SsdDao)
{
    val sveSsdStavke = this.dao.getSviSsdovi()

    suspend fun ukloniSsd(ssd: Ssd) = this.dao.ukloniSsd(ssd)
}