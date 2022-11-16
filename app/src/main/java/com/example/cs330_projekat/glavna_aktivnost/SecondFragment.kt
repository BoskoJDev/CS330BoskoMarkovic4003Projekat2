package com.example.cs330_projekat.glavna_aktivnost

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cs330_projekat.databinding.FragmentSecondBinding
import com.example.cs330_projekat.glavna_aktivnost.adapteri.*
import com.example.cs330_projekat.glavna_aktivnost.oprema.graficke_karte.GrafickaKartaModelPogleda
import com.example.cs330_projekat.glavna_aktivnost.oprema.hdd.HddModelPogleda
import com.example.cs330_projekat.glavna_aktivnost.oprema.maticne_ploce.MaticnaPlocaModelPogleda
import com.example.cs330_projekat.glavna_aktivnost.oprema.napajanja.NapajanjeModelPogleda
import com.example.cs330_projekat.glavna_aktivnost.oprema.procesor.ProcesorModelPogleda
import com.example.cs330_projekat.glavna_aktivnost.oprema.ram_memorije.RamMemorijaModelPogleda
import com.example.cs330_projekat.glavna_aktivnost.oprema.ssd.SsdModelPogleda

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment()
{
    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, grupa: ViewGroup?,b: Bundle?): View
    {
        _binding = FragmentSecondBinding.inflate(inflater, grupa, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        when (this.activity?.intent?.getSerializableExtra("tipKomponente") as Komponenta)
        {
            Komponenta.RAM -> {
                binding.tipProizvoda.text = "RAM memorije"

                val ramPogleda = ViewModelProvider(this)[RamMemorijaModelPogleda::class.java]
                val ramAdapter = RamAdapter(this)
                ramPogleda.sveMemorije.observe(viewLifecycleOwner) { memorije ->
                    ramAdapter.setLista(memorije)
                }

                binding.proizvodi.adapter = ramAdapter
                binding.proizvodi.layoutManager = LinearLayoutManager(this.requireContext())
            }
            Komponenta.SSD -> {
                binding.tipProizvoda.text = "SSD-ovi"

                val ssdPogleda = ViewModelProvider(this)[SsdModelPogleda::class.java]
                val ssdAdapter = SsdAdapter(this)
                ssdPogleda.sveSsdStavke.observe(viewLifecycleOwner) { ssdLista ->
                    ssdAdapter.setLista(ssdLista)
                }

                binding.proizvodi.adapter = ssdAdapter
                binding.proizvodi.layoutManager = LinearLayoutManager(this.requireContext())
            }
            Komponenta.MATICNA_PLOCA -> {
                binding.tipProizvoda.text = "Maticne ploce"

                val maticnaPogled = ViewModelProvider(this)[MaticnaPlocaModelPogleda::class.java]
                val procesorAdapter = MaticnaPlocaAdapter(this)
                maticnaPogled.sveMaticnePloce.observe(viewLifecycleOwner) { ploce ->
                    procesorAdapter.setLista(ploce)
                }

                binding.proizvodi.adapter = procesorAdapter
                binding.proizvodi.layoutManager = LinearLayoutManager(this.requireContext())
            }
            Komponenta.HDD -> {
                binding.tipProizvoda.text = "HDD-ovi"

                val hddPogled = ViewModelProvider(this)[HddModelPogleda::class.java]
                val hddAdapter = HddAdapter(this)
                hddPogled.sviHddovi.observe(viewLifecycleOwner) { ploce ->
                    hddAdapter.setLista(ploce)
                }

                binding.proizvodi.adapter = hddAdapter
                binding.proizvodi.layoutManager = LinearLayoutManager(this.requireContext())
            }
            Komponenta.PROCESOR -> {
                binding.tipProizvoda.text = "Procesori"

                val procesorPogled = ViewModelProvider(this)[ProcesorModelPogleda::class.java]
                val procesorAdapter = ProcesorAdapter(this)
                procesorPogled.sviProcesori.observe(viewLifecycleOwner) { procesori ->
                    procesorAdapter.setLista(procesori)
                }

                binding.proizvodi.adapter = procesorAdapter
                binding.proizvodi.layoutManager = LinearLayoutManager(this.requireContext())
            }
            Komponenta.GRAFICKA_KARTA -> {
                binding.tipProizvoda.text = "Graficke karte"

                val grafickaKartaPogled = ViewModelProvider(this)[GrafickaKartaModelPogleda::class.java]
                val grafickaKartaAdapter = GrafickaKartaAdapter(this)
                grafickaKartaPogled.sveGrafickaKarte.observe(viewLifecycleOwner) { karte ->
                    grafickaKartaAdapter.setLista(karte)
                }

                binding.proizvodi.adapter = grafickaKartaAdapter
                binding.proizvodi.layoutManager = LinearLayoutManager(this.requireContext())
            }
            Komponenta.NAPAJANJE -> {
                binding.tipProizvoda.text = "Napajanja"

                val napajanjePogled = ViewModelProvider(this)[NapajanjeModelPogleda::class.java]
                val napajanjeAdapter = NapajanjeAdapter(this)
                napajanjePogled.svaNapajanja.observe(viewLifecycleOwner) { napajanja ->
                    napajanjeAdapter.setLista(napajanja)
                }

                binding.proizvodi.adapter = napajanjeAdapter
                binding.proizvodi.layoutManager = LinearLayoutManager(this.requireContext())
            }
        }
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}