package com.example.apilist_sergiherrador.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apilist_sergiherrador.Model.AllFilms
import com.example.apilist_sergiherrador.Model.SpeciesItem

class ListDetailScreenViewModel : ViewModel() {
    private var ghibli: AllFilms by mutableStateOf(AllFilms("", "", "", "", "", "", ""))

    private var show by mutableStateOf(false)

    private var specie:SpeciesItem by mutableStateOf(SpeciesItem("","", emptyList(), "", "","", emptyList(), ""))

    fun pillarShow():Boolean{
        return show
    }

    fun modificarShow(valor:Boolean){
        show = valor
    }

    fun pillarGhibli():AllFilms{
        return ghibli
    }

    fun modificarGhibli(dataItem: AllFilms){
        ghibli = dataItem
    }

    fun pillarSpecie():SpeciesItem{
        return specie
    }
    fun modificarSpecie(speciesItem: SpeciesItem){
        specie = speciesItem
    }

    private val _status: MutableLiveData<Boolean> = MutableLiveData(false)

    val status: LiveData<Boolean> = _status

    fun setStatus(value: Boolean) {
        _status.value = value
    }
}

