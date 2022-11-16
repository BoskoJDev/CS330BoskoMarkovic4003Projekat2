package com.example.cs330_projekat.glavna_aktivnost.oprema.ram_memorije

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cs330_projekat.glavna_aktivnost.oprema.Proizvod
import java.io.Serializable

@Entity(tableName = "memorije")
data class RamMemorija(
    override val naziv: String,
    val memorija: Int,
    val tipMemorije: String,
    override val cena: Int,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Serializable, Proizvod
