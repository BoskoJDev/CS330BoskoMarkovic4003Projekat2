package com.example.cs330_projekat.glavna_aktivnost.adapteri

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cs330_projekat.R
import com.example.cs330_projekat.glavna_aktivnost.korisnik.Korisnik
import com.example.cs330_projekat.glavna_aktivnost.oprema.graficke_karte.GrafickaKarta

class GrafickaKartaAdapter(private val fragment: Fragment) : RecyclerView.Adapter<GrafickaKartaAdapter.DrzacPogleda>()
{
    var lista = ArrayList<GrafickaKarta>()

    fun setLista(list: List<GrafickaKarta>)
    {
        this.lista = ArrayList(list)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrzacPogleda
    {
        return DrzacPogleda(LayoutInflater.from(parent.context).inflate(R.layout.graficka_karta, parent, false))
    }

    override fun onBindViewHolder(holder: DrzacPogleda, position: Int)
    {
        val graficka = this.lista[position]

        holder.naziv.text = "Naziv: " + graficka.naziv
        holder.cena.text = "Cena: " + graficka.cena

        holder.roditelj.setOnClickListener {
            AlertDialog.Builder(this.fragment.requireContext())
                .setMessage("Naziv: " + graficka.naziv + "\nVRAM: " + graficka.vram + "GB\n"
                + "Tip memorije: " + graficka.tipMemorije + "\nCena: " + graficka.cena + " din")
                .setPositiveButton("Dodaj u korpu") { _, _ ->
                    val namera = this.fragment.activity?.intent
                    val korisnik = namera?.extras?.getSerializable("korisnik") as Korisnik
                    korisnik.dodajProizvod(graficka)
                    namera.putExtra("korisnik", korisnik)
                    this.fragment.findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
                }
                .setNegativeButton("Nazad", null)
                .create().show()
        }
    }

    override fun getItemCount(): Int = this.lista.size

    class DrzacPogleda(pogled: View) : RecyclerView.ViewHolder(pogled)
    {
        val naziv: TextView
        val cena: TextView
        val roditelj: CardView

        init
        {
            this.roditelj = pogled.findViewById(R.id.grafikaRoditelj)
            this.naziv = pogled.findViewById(R.id.nazivGraficke)
            this.cena = pogled.findViewById(R.id.cenaGraficke)
        }
    }
}