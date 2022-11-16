package com.example.cs330_projekat.glavna_aktivnost

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.cs330_projekat.R

class DijalogPretrage : AppCompatDialogFragment()
{
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog
    {
        val dijalog = AlertDialog.Builder(activity)
        val pogled = activity?.layoutInflater?.inflate(R.layout.layout_dijalog, null)

        return dijalog.setView(pogled)
            .setNegativeButton("Izadji", null)
            .setPositiveButton("Pretrazi", null)
            .create()
    }
}