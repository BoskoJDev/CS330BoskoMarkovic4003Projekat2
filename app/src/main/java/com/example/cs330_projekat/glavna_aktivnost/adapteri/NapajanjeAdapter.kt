package com.example.cs330_projekat.glavna_aktivnost.adapteri

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cs330_projekat.R
import com.example.cs330_projekat.glavna_aktivnost.korisnik.Korisnik
import com.example.cs330_projekat.glavna_aktivnost.oprema.napajanja.Napajanje

class NapajanjeAdapter(private val fragment: Fragment) : RecyclerView.Adapter<NapajanjeAdapter.DrzacPogleda>()
{
    var lista = ArrayList<Napajanje>()

    fun setLista(list: List<Napajanje>)
    {
        this.lista = ArrayList(list)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): DrzacPogleda
    {
        return DrzacPogleda(LayoutInflater.from(parent.context).inflate(R.layout.napajanje, parent, false))
    }

    override fun onBindViewHolder(holder: DrzacPogleda, position: Int)
    {
        val napajanje = this.lista[position]

        holder.naziv.text = "Naziv: " + napajanje.naziv
        holder.cena.text = "Cena: " + napajanje.cena
        holder.roditelj.setOnClickListener {
            AlertDialog.Builder(this.fragment.requireContext())
                .setMessage("Naziv: " + napajanje.naziv + "\nNapon: " + napajanje.napon +
                "W\nCena: " + napajanje.cena + " din")
                .setPositiveButton("Dodaj u korpu") { _, _ ->
                    val namera = this.fragment.activity?.intent
                    val korisnik = namera?.extras?.getSerializable("korisnik") as Korisnik
                    korisnik.dodajProizvod(napajanje)
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
            this.naziv = pogled.findViewById(R.id.nazivNapajanja)
            this.cena = pogled.findViewById(R.id.cenaNapajanja)
            this.roditelj = pogled.findViewById(R.id.napajanjeRoditelj)
        }
    }
}