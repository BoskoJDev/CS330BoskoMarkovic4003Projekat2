package com.example.cs330_projekat.glavna_aktivnost.oprema.graficke_karte

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cs330_projekat.glavna_aktivnost.oprema.Proizvod
import java.io.Serializable

@Entity(tableName = "graficke_karte")
data class GrafickaKarta(
    val vram: Int,
    val tipMemorije: String,
    override val naziv: String,
    override val cena: Int,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Serializable, Proizvod