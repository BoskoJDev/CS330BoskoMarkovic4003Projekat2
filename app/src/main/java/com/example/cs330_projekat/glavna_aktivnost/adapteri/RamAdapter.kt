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
import com.example.cs330_projekat.glavna_aktivnost.oprema.ram_memorije.RamMemorija

class RamAdapter(private val fragment: Fragment) : RecyclerView.Adapter<RamAdapter.DrzacPogleda>()
{
    private var lista = ArrayList<RamMemorija>()

    fun setLista(list: List<RamMemorija>)
    {
        this.lista = ArrayList(list)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(roditelj: ViewGroup, tip: Int): DrzacPogleda
    {
        return DrzacPogleda(LayoutInflater.from(roditelj.context).inflate(R.layout.ram_memorija, roditelj, false))
    }

    override fun onBindViewHolder(holder: DrzacPogleda, pozicija: Int)
    {
        val ramMemorija = this.lista[pozicija]

        holder.naziv.text = "Naziv: " + ramMemorija.naziv
        holder.cena.text = "Cena: " + ramMemorija.cena + " din"
        holder.roditelj.setOnClickListener {
            AlertDialog.Builder(this.fragment.requireContext())
                .setMessage("Naziv: " + ramMemorija.naziv + "\nMemorija: " + ramMemorija.memorija
                + " GB\nTip memorije: " + ramMemorija.tipMemorije + "\nCena: " + ramMemorija.cena + " din")
                .setPositiveButton("Dodaj u korpu") { _, _ ->
                    val namera = this.fragment.activity?.intent
                    val korisnik = namera?.extras?.getSerializable("korisnik") as Korisnik
                    korisnik.dodajProizvod(ramMemorija)
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
            this.naziv = pogled.findViewById(R.id.naziv)
            this.cena = pogled.findViewById(R.id.cena)
            this.roditelj = pogled.findViewById(R.id.ramRoditelj)
        }
    }
}