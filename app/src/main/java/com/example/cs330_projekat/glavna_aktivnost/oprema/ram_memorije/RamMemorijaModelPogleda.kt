package com.example.cs330_projekat.glavna_aktivnost.oprema.ram_memorije

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cs330_projekat.glavna_aktivnost.baza.Baza
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RamMemorijaModelPogleda(aplikacija: Application) : AndroidViewModel(aplikacija)
{
    val sveMemorije: LiveData<List<RamMemorija>>
    val repozitorijum: RamMemorijaRepozitorijum

    init
    {
        this.repozitorijum = RamMemorijaRepozitorijum(Baza.getBaza(aplikacija).getRamDao())
        this.sveMemorije = this.repozitorijum.sveMemorije
    }

    fun ukloniMemoriju(ram: RamMemorija)
    {
        viewModelScope.launch(Dispatchers.IO) { repozitorijum.ukloniMemoriju(ram) }
    }
}