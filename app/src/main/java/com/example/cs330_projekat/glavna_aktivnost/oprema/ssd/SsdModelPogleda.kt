package com.example.cs330_projekat.glavna_aktivnost.oprema.ssd

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cs330_projekat.glavna_aktivnost.baza.Baza
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SsdModelPogleda(aplikacija: Application) : AndroidViewModel(aplikacija)
{
    val sveSsdStavke: LiveData<List<Ssd>>
    val repozitorijum: SsdRepozitorijum

    init
    {
        this.repozitorijum = SsdRepozitorijum(Baza.getBaza(aplikacija).getSsdDao())
        this.sveSsdStavke = this.repozitorijum.sveSsdStavke
    }

    fun ukloniSsd(ssd: Ssd) = viewModelScope.launch(Dispatchers.IO) { repozitorijum.ukloniSsd(ssd) }
}