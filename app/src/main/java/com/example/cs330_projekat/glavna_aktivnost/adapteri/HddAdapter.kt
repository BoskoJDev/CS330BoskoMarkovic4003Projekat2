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
import com.example.cs330_projekat.glavna_aktivnost.oprema.hdd.Hdd

class HddAdapter(private val fragment: Fragment) : RecyclerView.Adapter<HddAdapter.DrzacPogleda>()
{
    var lista = ArrayList<Hdd>()

    fun setLista(list: List<Hdd>)
    {
        this.lista = ArrayList(list)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrzacPogleda
    {
        return DrzacPogleda(LayoutInflater.from(parent.context).inflate(R.layout.hdd, parent, false))
    }

    override fun onBindViewHolder(holder: DrzacPogleda, position: Int)
    {
        val hdd = this.lista[position]

        holder.naziv.text = "Naziv: " + hdd.naziv
        holder.cena.text = "Cena: " + hdd.cena

        val kapacitet: String = if (hdd.kapacitet % 1024 == 0)
            (hdd.kapacitet / 1024).toString() + " TB\n"
        else
            hdd.kapacitet.toString() + " GB\n"

        holder.roditelj.setOnClickListener {
            AlertDialog.Builder(this.fragment.requireContext())
                .setMessage("Naziv: " + hdd.naziv + "\nKapacitet: " + kapacitet
                + "Cena: " + hdd.cena)
                .setPositiveButton("Dodaj u korpu") { _, _ ->
                    val namera = this.fragment.activity?.intent
                    val korisnik = namera?.extras?.getSerializable("korisnik") as Korisnik
                    korisnik.dodajProizvod(hdd)
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
            this.naziv = pogled.findViewById(R.id.nazivHdd)
            this.cena = pogled.findViewById(R.id.cenaHdd)
            this.roditelj = pogled.findViewById(R.id.hddRoditelj)
        }
    }
}