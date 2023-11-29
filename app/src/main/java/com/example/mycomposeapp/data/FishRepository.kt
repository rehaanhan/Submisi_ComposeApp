package com.example.mycomposeapp.data

import com.example.mycomposeapp.model.Ikan
import com.example.mycomposeapp.model.IkanData

class FishRepository {
    fun getAllFish(): List<Ikan>{
        return IkanData.ikanList
    }

    fun searchIkan(search: String): List<Ikan>{
        return IkanData.ikanList.filter {
            it.name.contains(search, ignoreCase = true)
        }
    }

    fun searchFishById(id: String): Ikan{
        return IkanData.ikanList.first{
            it.id == id
        }
    }

}