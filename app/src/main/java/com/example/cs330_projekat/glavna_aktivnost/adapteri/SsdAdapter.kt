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
import com.example.cs330_projekat.glavna_aktivnost.oprema.ssd.Ssd

class SsdAdapter(private val fragment: Fragment) : RecyclerView.Adapter<SsdAdapter.DrzacPogleda>()
{
    private var lista = ArrayList<Ssd>()

    fun setLista(list: List<Ssd>)
    {
        this.lista = ArrayList(list)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(roditelj: ViewGroup, viewType: Int): DrzacPogleda
    {
        return DrzacPogleda(LayoutInflater.from(roditelj.context).inflate(R.layout.ssd, roditelj, false))
    }

    override fun getItemCount(): Int = this.lista.size

    override fun onBindViewHolder(holder: DrzacPogleda, pozicija: Int)
    {
        val ssd = this.lista[pozicija]

        holder.naziv.text = "Naziv: " + ssd.naziv
        holder.cena.text = "Cena: " + ssd.cena + " din"

        val kapacitet: String = if (ssd.kapacitet % 1024 == 0)
            (ssd.kapacitet / 1024).toString() + " TB\n"
        else
            ssd.kapacitet.toString() + " GB\n"

        holder.roditelj.setOnClickListener {
            AlertDialog.Builder(this.fragment.requireContext())
                .setMessage("Naziv: " + ssd.naziv + "\nKapacitet: " + kapacitet + "Tip: "
                + ssd.tip + "\nCena: " + ssd.cena + " din")
                .setPositiveButton("Dodaj u korpu") { _, _ ->
                    val namera = this.fragment.activity?.intent
                    val korisnik = namera?.extras?.getSerializable("korisnik") as Korisnik
                    korisnik.dodajProizvod(ssd)
                    namera.putExtra("korisnik", korisnik)
                    this.fragment.findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
                }
                .setNegativeButton("Nazad", null)
                .create().show()
        }
    }

    class DrzacPogleda(pogled: View) : RecyclerView.ViewHolder(pogled)
    {
        val naziv: TextView
        val cena: TextView
        val roditelj: CardView

        init
        {
            this.naziv = pogled.findViewById(R.id.nazivSsd)
            this.cena = pogled.findViewById(R.id.cenaSsd)
            this.roditelj = pogled.findViewById(R.id.ssdRoditelj)
        }
    }
}