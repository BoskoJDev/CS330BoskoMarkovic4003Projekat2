package com.example.cs330_projekat.glavna_aktivnost.oprema.hdd

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cs330_projekat.glavna_aktivnost.baza.Baza

class HddModelPogleda(aplikacija: Application) : AndroidViewModel(aplikacija)
{
    val sviHddovi: LiveData<List<Hdd>>
    val repozitorijum: HddRepozitorijum

    init
    {
        this.repozitorijum = HddRepozitorijum(Baza.getBaza(aplikacija).getHddDao())
        this.sviHddovi = this.repozitorijum.sviHddovi
    }
}