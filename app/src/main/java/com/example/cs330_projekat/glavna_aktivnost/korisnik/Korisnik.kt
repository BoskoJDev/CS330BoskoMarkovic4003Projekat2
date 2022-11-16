package com.example.cs330_projekat.glavna_aktivnost.korisnik

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.cs330_projekat.glavna_aktivnost.oprema.Proizvod
import java.io.Serializable

@Entity(tableName = "korisnici")
data class Korisnik(
    val imejl: String,
    val sifra: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Serializable
{
    @Ignore
    private val naruceno = ArrayList<Proizvod>()

    fun dodajProizvod(proizvod: Proizvod) = this.naruceno.add(proizvod)

    fun isprazni() = this.naruceno.clear()

    fun cenaPorudzbine(): Int
    {
        var cena = 0
        for (proizvod in this.naruceno)
            cena += proizvod.cena

        return cena
    }

    fun porudzbina(): String
    {
        var string = "Vasa porudzbina:\n"
        for (proizvod in this.naruceno)
            string += proizvod.naziv

        return string
    }

    fun imaProizvoda() = this.naruceno.isNotEmpty()
}