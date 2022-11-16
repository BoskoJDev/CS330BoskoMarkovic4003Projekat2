package com.example.cs330_projekat.glavna_aktivnost.oprema.napajanja

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cs330_projekat.glavna_aktivnost.oprema.Proizvod
import java.io.Serializable

@Entity(tableName = "napajanja")
data class Napajanje(
    override val naziv: String,
    val napon: Int,
    val faktor: String,
    override val cena: Int,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Serializable, Proizvod