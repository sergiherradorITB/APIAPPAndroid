package com.example.apilist_sergiherrador.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apilist_sergiherrador.Model.AllFilms
import com.example.apilist_sergiherrador.Model.DetailFilmItem
import com.example.apilist_sergiherrador.Model.LocationItem
import com.example.apilist_sergiherrador.Model.SpeciesItem

class ListDetailScreenViewModel : ViewModel() {
    private var ghibli: AllFilms by mutableStateOf(AllFilms("", "", "", "", "", "", ""))

    private var id:String by mutableStateOf("")
    private var show by mutableStateOf(false)

    private var specie: SpeciesItem by mutableStateOf(
        SpeciesItem(
            "",
            "",
            emptyList(),
            "",
            "",
            "",
            emptyList(),
            ""
        )
    )

    private var location: LocationItem by mutableStateOf(
        LocationItem(
            "",
            emptyList(),
            "",
            "",
            emptyList(),
            "",
            "",
            ""
        )
    )

    private var ghibliFavorite: DetailFilmItem by mutableStateOf(
        DetailFilmItem(
            "", "", "", "",
            emptyList(), "", "", "", emptyList(), "", "", "", "", emptyList(), "", "", emptyList(),
        )
    )

    fun pillarShow(): Boolean {
        return show
    }

    fun modificarShow(valor: Boolean) {
        show = valor
    }

    fun pillarGhibli(): AllFilms {
        return ghibli
    }
    fun pillarGhibliId():String{
        return id
    }
    fun modificarGhibliId(id:String){
        this.id = id
    }

    fun modificarGhibli(dataItem: AllFilms) {
        ghibli = dataItem
    }

    fun modificarGhibliFavorite(dataItem: DetailFilmItem) {
        ghibliFavorite = dataItem
    }

    fun pillarSpecie(): SpeciesItem {
        return specie
    }

    fun modificarSpecie(speciesItem: SpeciesItem) {
        specie = speciesItem
    }

    fun pillarLocation(): LocationItem {
        return location
    }

    fun modificarLocation(locationItem: LocationItem) {
        location = locationItem
    }

    private val _status: MutableLiveData<Boolean> = MutableLiveData(false)

    val status: LiveData<Boolean> = _status

    fun setStatus(value: Boolean) {
        _status.value = value
    }
}

