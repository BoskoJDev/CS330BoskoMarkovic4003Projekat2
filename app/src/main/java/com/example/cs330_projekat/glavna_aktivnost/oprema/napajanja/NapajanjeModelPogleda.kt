package com.example.cs330_projekat.glavna_aktivnost.oprema.napajanja

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cs330_projekat.glavna_aktivnost.baza.Baza

class NapajanjeModelPogleda(aplikacija: Application) : AndroidViewModel(aplikacija)
{
    val svaNapajanja: LiveData<List<Napajanje>>
    val repozitorijum: NapajanjeRepozitorijum

    init
    {
        this.repozitorijum = NapajanjeRepozitorijum(Baza.getBaza(aplikacija).getNapajanjeDao())
        this.svaNapajanja = this.repozitorijum.svaNapajanja
    }
}