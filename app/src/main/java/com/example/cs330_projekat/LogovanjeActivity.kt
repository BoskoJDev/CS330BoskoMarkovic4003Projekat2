package com.example.cs330_projekat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.cs330_projekat.glavna_aktivnost.GlavnaAktivnostActivity
import com.example.cs330_projekat.glavna_aktivnost.korisnik.Korisnik
import com.example.cs330_projekat.glavna_aktivnost.korisnik.KorisnikModelPogleda

class LogovanjeActivity : AppCompatActivity()
{
    private lateinit var modelPogleda: KorisnikModelPogleda
    private lateinit var poljeNaloga: TextView
    private lateinit var poljeSifre: TextView
    private lateinit var zaboravljenaLozinka: TextView
    private lateinit var ulogujSe: Button
    private lateinit var noviNalog: Button
    private var korisnikPronadjen = false

    private fun proveraUnosa(): Boolean
    {
        if (this.poljeNaloga.text.isNullOrBlank() || this.poljeSifre.text.isNullOrBlank())
        {
            Toast.makeText(this, "Unesite podatke!", Toast.LENGTH_SHORT).show()
            return false
        }

        this.modelPogleda.sviKorisnici.observe(this) { korisnici ->
            for (user in korisnici)
            {
                this.korisnikPronadjen = user.imejl == this.poljeNaloga.text.toString() &&
                        user.sifra == this.poljeSifre.text.toString()

                if (this.korisnikPronadjen)
                    break
            }
        }

        if (!this.korisnikPronadjen)
        {
            Toast.makeText(this, "Korisnik nije pronadjen!", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.modelPogleda = ViewModelProvider(this)[KorisnikModelPogleda::class.java]

        this.ulogujSe = findViewById(R.id.ulogujSe)
        this.noviNalog = findViewById(R.id.noviNalog)
        this.zaboravljenaLozinka = findViewById(R.id.textView4)
        this.poljeNaloga = findViewById(R.id.poljeNaloga)
        this.poljeSifre = findViewById(R.id.poljeSifre)

        ulogujSe.setOnClickListener {
            if (!this.proveraUnosa())
                return@setOnClickListener

            val bandl = Bundle()
            bandl.putSerializable("ulogovan",
                Korisnik(this.poljeNaloga.text.toString(),this.poljeSifre.text.toString())
            )
            val namera = Intent(this, GlavnaAktivnostActivity::class.java)
            namera.putExtras(bandl)
            startActivity(namera)
        }

        noviNalog.setOnClickListener { startActivity(Intent(this, NovNalogActivity::class.java)) }

        this.zaboravljenaLozinka.setOnClickListener { startActivity(Intent(this, NovNalogActivity::class.java)) }
    }

    override fun onBackPressed()
    {
        super.onBackPressed()
        super.moveTaskToBack(true)
        super.finishActivity(0)
    }
}