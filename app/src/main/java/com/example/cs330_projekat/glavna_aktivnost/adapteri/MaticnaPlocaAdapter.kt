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
import com.example.cs330_projekat.glavna_aktivnost.oprema.maticne_ploce.MaticnaPloca

class MaticnaPlocaAdapter(private val fragment: Fragment) : RecyclerView.Adapter<MaticnaPlocaAdapter.DrzacPogleda>()
{
    private var lista = ArrayList<MaticnaPloca>()

    fun setLista(list: List<MaticnaPloca>)
    {
        this.lista = ArrayList(list)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrzacPogleda
    {
        return DrzacPogleda(LayoutInflater.from(parent.context).inflate(R.layout.maticna_ploca, parent, false))
    }

    override fun onBindViewHolder(holder: DrzacPogleda, pozicija: Int)
    {
        val maticna = this.lista[pozicija]

        holder.naziv.text = "Naziv: " + maticna.naziv
        holder.cena.text = "Cena: " + maticna.cena

        holder.roditelj.setOnClickListener {
            AlertDialog.Builder(this.fragment.requireContext())
                .setMessage("Naziv: " + maticna.naziv + "\nUticnica: " + maticna.uticnica
                + "\nTip procesora: " + maticna.tipProcesora + "\nMaksimalan broj slotova memorije: "
                + maticna.maxMemorija + "\nCena: " + maticna.cena + " din")
                .setPositiveButton("Dodaj u korpu") { _, _ ->
                    val namera = this.fragment.activity?.intent
                    val korisnik = namera?.extras?.getSerializable("korisnik") as Korisnik
                    korisnik.dodajProizvod(maticna)
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
            this.naziv = pogled.findViewById(R.id.nazivMaticne)
            this.cena = pogled.findViewById(R.id.cenaMaticne)
            this.roditelj = pogled.findViewById(R.id.maticniRoditelj)
        }
    }
}