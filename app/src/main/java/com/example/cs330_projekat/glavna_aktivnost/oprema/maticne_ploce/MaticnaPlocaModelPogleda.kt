package com.example.cs330_projekat.glavna_aktivnost.oprema.maticne_ploce

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cs330_projekat.glavna_aktivnost.baza.Baza
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MaticnaPlocaModelPogleda(aplikacija: Application) : AndroidViewModel(aplikacija)
{
    val sveMaticnePloce: LiveData<List<MaticnaPloca>>
    val repozitorijum: MaticnaPlocaRepozitorijum

    init
    {
        this.repozitorijum = MaticnaPlocaRepozitorijum(Baza.getBaza(aplikacija).getMaticnaPlocaDao())
        this.sveMaticnePloce = this.repozitorijum.sveMaticne
    }

    fun ukloniMaticnuPlocu(ploca: MaticnaPloca)
    {
        viewModelScope.launch(Dispatchers.IO) { repozitorijum.ukloniMaticnuPlocu(ploca) }
    }
}