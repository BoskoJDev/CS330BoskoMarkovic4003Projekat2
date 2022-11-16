package com.example.cs330_projekat.glavna_aktivnost.oprema.maticne_ploce

class MaticnaPlocaRepozitorijum(private val maticnaPlocaDao: MaticnaPlocaDao)
{
    val sveMaticne = this.maticnaPlocaDao.getSveMaticnePloce()

    suspend fun ukloniMaticnuPlocu(ploca: MaticnaPloca) = this.maticnaPlocaDao.ukloniMaticnuPlocu(ploca)
}