package com.example.cs330_projekat.glavna_aktivnost.oprema.ssd

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cs330_projekat.glavna_aktivnost.oprema.Proizvod
import java.io.Serializable

@Entity(tableName = "ssd_ovi")
data class Ssd(
    override val naziv: String,
    val kapacitet: Int,
    val tip: String,
    override val cena: Int,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Serializable, Proizvod