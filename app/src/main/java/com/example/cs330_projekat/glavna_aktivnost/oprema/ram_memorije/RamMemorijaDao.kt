package com.example.cs330_projekat.glavna_aktivnost.oprema.ram_memorije

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query

@Dao
interface RamMemorijaDao
{
    @Delete
    suspend fun izbrisiMemoriju(ram: RamMemorija)

    @Query("select * from memorije")
    fun getSveMemorije(): LiveData<List<RamMemorija>>
}