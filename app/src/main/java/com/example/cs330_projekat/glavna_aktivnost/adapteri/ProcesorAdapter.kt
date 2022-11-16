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
import com.example.cs330_projekat.glavna_aktivnost.oprema.procesor.Procesor

class ProcesorAdapter(private val fragment: Fragment) : RecyclerView.Adapter<ProcesorAdapter.DrzacPogleda>()
{
    var lista = ArrayList<Procesor>()

    fun setLista(list: List<Procesor>)
    {
        this.lista = ArrayList(list)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrzacPogleda
    {
        return DrzacPogleda(LayoutInflater.from(parent.context).inflate(R.layout.procesor, parent, false))
    }

    override fun onBindViewHolder(holder: DrzacPogleda, position: Int)
    {
        val procesor = this.lista[position]

        holder.naziv.text = "Naziv: " + procesor.naziv
        holder.cena.text = "Cena: " + procesor.cena
        holder.roditelj.setOnClickListener {
            AlertDialog.Builder(this.fragment.requireContext())
                .setMessage("Naziv: " + procesor.naziv + "\nTakt: " + procesor.takt + " Ghz\n"
                + "Broj jezgara: " + procesor.jezgra + "\nBroj niti: " + procesor.niti + "\n"
                + "Cena: " + procesor.cena + " din")
                .setPositiveButton("Dodaj u korpu") { _, _ ->
                    val namera = this.fragment.activity?.intent
                    val korisnik = namera?.extras?.getSerializable("korisnik") as Korisnik
                    korisnik.dodajProizvod(procesor)
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
            this.naziv = pogled.findViewById(R.id.nazivProcesora)
            this.cena = pogled.findViewById(R.id.cenaProcesora)
            this.roditelj = pogled.findViewById(R.id.procesorRoditelj)
        }
    }
}