package com.example.apilist_sergiherrador.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apilist_sergiherrador.Model.AllFilms

class ListDetailScreenViewModel : ViewModel() {
    private var ghibli: AllFilms by mutableStateOf(AllFilms("", "", "", "", "", "", ""))

    private var show by mutableStateOf(false)

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
    private val _status: MutableLiveData<Boolean> = MutableLiveData(false)

    val status: LiveData<Boolean> = _status

    fun setStatus(value: Boolean) {
        _status.value = value
    }
}

