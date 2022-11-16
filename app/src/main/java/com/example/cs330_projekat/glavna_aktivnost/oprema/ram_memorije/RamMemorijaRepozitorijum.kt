package com.example.cs330_projekat.glavna_aktivnost.oprema.ram_memorije

class RamMemorijaRepozitorijum(private val ramDao: RamMemorijaDao)
{
    val sveMemorije = this.ramDao.getSveMemorije()

    suspend fun ukloniMemoriju(memorija: RamMemorija) = this.ramDao.izbrisiMemoriju(memorija)
}