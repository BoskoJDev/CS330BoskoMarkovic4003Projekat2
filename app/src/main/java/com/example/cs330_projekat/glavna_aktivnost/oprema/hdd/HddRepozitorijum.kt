package com.example.cs330_projekat.glavna_aktivnost.oprema.hdd

class HddRepozitorijum(private val dao: HddDao)
{
    val sviHddovi = this.dao.getSveHdd()

    suspend fun ukloniHdd(hdd: Hdd) = this.dao.ukloniHdd(hdd)
}