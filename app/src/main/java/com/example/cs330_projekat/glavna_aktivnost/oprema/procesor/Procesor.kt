package com.example.cs330_projekat.glavna_aktivnost.oprema.procesor

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cs330_projekat.glavna_aktivnost.oprema.Proizvod
import java.io.Serializable

@Entity(tableName = "procesori")
data class Procesor(
    val takt: Float,
    override val naziv: String,
    val jezgra: Int,
    val niti: Int,
    override val cena: Int,
    @PrimaryKey(autoGenerate = true) val id: Int
) : Serializable, Proizvod