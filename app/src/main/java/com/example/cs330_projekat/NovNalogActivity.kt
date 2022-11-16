package com.example.cs330_projekat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.cs330_projekat.glavna_aktivnost.korisnik.Korisnik
import com.example.cs330_projekat.glavna_aktivnost.korisnik.KorisnikModelPogleda

class NovNalogActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nov_nalog)

        val nalog = findViewById<TextView>(R.id.nalog)
        val sifra = findViewById<TextView>(R.id.sifra)
        val potvrdaSifre = findViewById<TextView>(R.id.potvrdaSifre)
        val potvrda = findViewById<Button>(R.id.potvrda)
        val nazad = findViewById<Button>(R.id.nazad)

        potvrda.setOnClickListener {
            if (!this.proveraUnosa(nalog, sifra, potvrdaSifre))
                return@setOnClickListener

            AlertDialog.Builder(this).setMessage("Uspesno ste kreirali nalog!")
            .setNeutralButton("U redu") { _, _ ->
                startActivity(Intent(this, LogovanjeActivity::class.java))
            }
            .create().show()
        }

        nazad.setOnClickListener {
            if (nalog.text.toString() != "" || sifra.text.toString() != "" || potvrdaSifre.text.toString() != "")
            {
                AlertDialog.Builder(this).setMessage("Sigurno zelite da se vratite?")
                .setPositiveButton("Da") { _, _ ->
                    startActivity(Intent(this, LogovanjeActivity::class.java))
                }
                .setNegativeButton("Ne", null)
                .create().show()
            }

            startActivity(Intent(this, LogovanjeActivity::class.java))
        }
    }

    private fun proveraUnosa(nalog: TextView, sifra: TextView, potvrda: TextView): Boolean
    {
        val modelPogleda = ViewModelProvider(this).get(KorisnikModelPogleda::class.java)

        if (nalog.text.isNullOrBlank() && sifra.text.isNullOrBlank() && potvrda.text.isNullOrBlank())
        {
            Toast.makeText(this, "Unesite podatke!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (sifra.text.toString() != potvrda.text.toString())
        {
            Toast.makeText(this, "Sifre se ne podudaraju!", Toast.LENGTH_SHORT).show()
            return false
        }

        modelPogleda.dodajKorisnika(Korisnik(nalog.text.toString(), sifra.text.toString()))
        return true
    }
}