package com.example.cs330_projekat.glavna_aktivnost.oprema.maticne_ploce

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cs330_projekat.glavna_aktivnost.oprema.Proizvod
import java.io.Serializable

@Entity(tableName = "maticne_ploce")
data class MaticnaPloca(
    override val naziv: String,
    val uticnica: String,
    val tipProcesora: String,
    val maxMemorija: Int,
    override val cena: Int,
    @PrimaryKey(autoGenerate = true) val id: Int
) : Serializable, Proizvod