package com.example.cs330_projekat.glavna_aktivnost.oprema.napajanja

class NapajanjeRepozitorijum(private val dao: NapajanjeDao)
{
    val svaNapajanja = this.dao.getSvaNapajanja()

    suspend fun ukloniNapajanje(n: Napajanje) = this.dao.ukloniNapajanje(n)
}