package com.example.cs330_projekat.glavna_aktivnost.baza

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.cs330_projekat.glavna_aktivnost.korisnik.KorisnikDao
import com.example.cs330_projekat.glavna_aktivnost.korisnik.Korisnik
import com.example.cs330_projekat.glavna_aktivnost.oprema.graficke_karte.GrafickaKartaDao
import com.example.cs330_projekat.glavna_aktivnost.oprema.graficke_karte.GrafickaKarta
import com.example.cs330_projekat.glavna_aktivnost.oprema.hdd.HddDao
import com.example.cs330_projekat.glavna_aktivnost.oprema.hdd.Hdd
import com.example.cs330_projekat.glavna_aktivnost.oprema.maticne_ploce.MaticnaPlocaDao
import com.example.cs330_projekat.glavna_aktivnost.oprema.maticne_ploce.MaticnaPloca
import com.example.cs330_projekat.glavna_aktivnost.oprema.napajanja.NapajanjeDao
import com.example.cs330_projekat.glavna_aktivnost.oprema.napajanja.Napajanje
import com.example.cs330_projekat.glavna_aktivnost.oprema.procesor.ProcesorDao
import com.example.cs330_projekat.glavna_aktivnost.oprema.procesor.Procesor
import com.example.cs330_projekat.glavna_aktivnost.oprema.ram_memorije.RamMemorijaDao
import com.example.cs330_projekat.glavna_aktivnost.oprema.ram_memorije.RamMemorija
import com.example.cs330_projekat.glavna_aktivnost.oprema.ssd.SsdDao
import com.example.cs330_projekat.glavna_aktivnost.oprema.ssd.Ssd

@Database(entities = [
    Korisnik::class,
    RamMemorija::class,
    Ssd::class,
    MaticnaPloca::class,
    Hdd::class,
    Procesor::class,
    GrafickaKarta::class,
    Napajanje::class
], version = 3, exportSchema = true)
abstract class Baza : RoomDatabase()
{
    abstract fun getKorisnikDao(): KorisnikDao
    abstract fun getRamDao(): RamMemorijaDao
    abstract fun getSsdDao(): SsdDao
    abstract fun getMaticnaPlocaDao(): MaticnaPlocaDao
    abstract fun getHddDao(): HddDao
    abstract fun getProcesorDao(): ProcesorDao
    abstract fun getGrafickaKartaDao(): GrafickaKartaDao
    abstract fun getNapajanjeDao(): NapajanjeDao

    companion object
    {
        @Volatile
        private var INSTANCA: Baza? = null

        fun getBaza(kontekst: Context): Baza
        {
            val tempInstanca = INSTANCA
            if (tempInstanca != null)
                return tempInstanca

            synchronized(this)
            {
                val instanca = Room.databaseBuilder(
                    kontekst.applicationContext,
                    Baza::class.java,
                    "baza"
                ).fallbackToDestructiveMigration()
                .createFromAsset("baza")
                .build()

                INSTANCA = instanca
                return instanca
            }
        }
    }
}