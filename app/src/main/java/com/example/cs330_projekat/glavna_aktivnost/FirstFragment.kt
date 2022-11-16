package com.example.cs330_projekat.glavna_aktivnost

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cs330_projekat.LogovanjeActivity
import com.example.cs330_projekat.R
import com.example.cs330_projekat.databinding.FragmentFirstBinding
import com.example.cs330_projekat.glavna_aktivnost.korisnik.Korisnik
import kotlinx.android.synthetic.main.content_forum.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment()
{
    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, grupa: ViewGroup?, b: Bundle?): View
    {
        Log.d("baza", this.requireActivity().applicationContext.filesDir.absolutePath)
        _binding = FragmentFirstBinding.inflate(inflater, grupa, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        binding.include.ram.setOnClickListener {
            this.activity?.intent?.putExtra("tipKomponente", Komponenta.RAM)
            val bandl = this.activity?.intent?.extras
            this.activity?.intent?.putExtra("korisnik", bandl?.getSerializable("ulogovan"))
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.include.ssd.setOnClickListener {
            this.activity?.intent?.putExtra("tipKomponente", Komponenta.SSD)
            val bandl = this.activity?.intent?.extras
            this.activity?.intent?.putExtra("korisnik", bandl?.getSerializable("ulogovan"))
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.include.maticne.setOnClickListener {
            this.activity?.intent?.putExtra("tipKomponente", Komponenta.MATICNA_PLOCA)
            val bandl = this.activity?.intent?.extras
            this.activity?.intent?.putExtra("korisnik", bandl?.getSerializable("ulogovan"))
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.include.hdd.setOnClickListener {
            this.activity?.intent?.putExtra("tipKomponente", Komponenta.HDD)
            val bandl = this.activity?.intent?.extras
            this.activity?.intent?.putExtra("korisnik", bandl?.getSerializable("ulogovan"))
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.include.procesori.setOnClickListener {
            this.activity?.intent?.putExtra("tipKomponente", Komponenta.PROCESOR)
            val bandl = this.activity?.intent?.extras
            this.activity?.intent?.putExtra("korisnik", bandl?.getSerializable("ulogovan"))
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.include.graficke.setOnClickListener {
            this.activity?.intent?.putExtra("tipKomponente", Komponenta.GRAFICKA_KARTA)
            val bandl = this.activity?.intent?.extras
            this.activity?.intent?.putExtra("korisnik", bandl?.getSerializable("ulogovan"))
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.include.napajanja.setOnClickListener {
            this.activity?.intent?.putExtra("tipKomponente", Komponenta.NAPAJANJE)
            val bandl = this.activity?.intent?.extras
            this.activity?.intent?.putExtra("korisnik", bandl?.getSerializable("ulogovan"))
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater)
    {
        super.onCreateOptionsMenu(menu, inflater)
        menu.add("Naruci")
        menu.add("Izloguj se")
        menu.add("Forum")
        menu.add("Pretrazi...")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when (item.title.toString())
        {
            "Naruci" -> {
                val k = this.requireActivity().intent.extras?.getSerializable("korisnik") as Korisnik
                if (!k.imaProizvoda())
                {
                    Toast.makeText(this.requireContext(), "Nista nije naruceno!", Toast.LENGTH_LONG).show()
                    return false
                }

                AlertDialog.Builder(this.requireContext())
                    .setMessage(k.porudzbina() + "\nCena porudzbine iznosi: " + k.cenaPorudzbine() + " din")
                    .setNeutralButton("U redu") { _, _ ->
                        this.requestPermissions(arrayOf(Manifest.permission.SEND_SMS), 1)

                    }
                    .create().show()
            }
            "Izloguj se" -> {
                this.activity?.startActivity(Intent(this.requireActivity(), LogovanjeActivity::class.java))
            }
            "Forum" -> {
                this.activity?.startActivity(Intent(this.requireActivity(), ForumActivity::class.java))
            }
            "Pretrazi..." -> {
                val dijalog = DijalogPretrage()
                dijalog.show(parentFragmentManager, "")
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode != 1)
            return

        Log.d("", (SmsManager.getDefault() == null).toString())
        val korisnik = this.requireActivity().intent.extras?.getSerializable("ulogovan") as Korisnik
        SmsManager.getDefault().sendTextMessage(korisnik.imejl, null, korisnik.porudzbina()
                + "\nCena: " + korisnik.cenaPorudzbine() + " dinara", null, null)

        korisnik.isprazni()
    }
}