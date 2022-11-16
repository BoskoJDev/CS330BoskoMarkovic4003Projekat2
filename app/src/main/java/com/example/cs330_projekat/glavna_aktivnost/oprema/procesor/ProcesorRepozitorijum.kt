package com.example.cs330_projekat.glavna_aktivnost.oprema.procesor

class ProcesorRepozitorijum(private val dao: ProcesorDao)
{
    val sviProcesori = this.dao.getSveProcesore()

    suspend fun ukloniProcesor(p: Procesor) = this.dao.ukloniProcesor(p)
}