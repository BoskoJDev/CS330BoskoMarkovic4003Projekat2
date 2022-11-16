package com.example.cs330_projekat.glavna_aktivnost.oprema.hdd

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cs330_projekat.glavna_aktivnost.oprema.Proizvod
import java.io.Serializable

@Entity(tableName = "hdd_ovi")
data class Hdd(
    override val naziv: String,
    val kapacitet: Int,
    override val cena: Int,
    @PrimaryKey(autoGenerate = true) val id: Int
) : Serializable, Proizvod