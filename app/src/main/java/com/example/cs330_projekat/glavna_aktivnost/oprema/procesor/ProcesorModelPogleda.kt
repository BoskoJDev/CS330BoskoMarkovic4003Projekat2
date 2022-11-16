package com.example.cs330_projekat.glavna_aktivnost.oprema.procesor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cs330_projekat.glavna_aktivnost.baza.Baza
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProcesorModelPogleda(aplikacija: Application) : AndroidViewModel(aplikacija)
{
    val sviProcesori: LiveData<List<Procesor>>
    val repozitorijum: ProcesorRepozitorijum

    init
    {
        this.repozitorijum = ProcesorRepozitorijum(Baza.getBaza(aplikacija).getProcesorDao())
        this.sviProcesori = this.repozitorijum.sviProcesori
    }

    fun ukloniProcesor(p: Procesor)
    {
        viewModelScope.launch(Dispatchers.IO) { repozitorijum.ukloniProcesor(p) }
    }
}