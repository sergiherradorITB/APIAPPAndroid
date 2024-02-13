package com.example.apilist_sergiherrador.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apilist_sergiherrador.Model.DataItem

class ListScreenViewModel : ViewModel() {
    private var ghibli: DataItem by mutableStateOf(DataItem("", "", "", "", listOf(), "", "", "", listOf(), "", "", "", "", listOf(), "", "", listOf()))

    fun pillarGhibli():DataItem{
        return ghibli
    }

    fun modificarGhibli(dataItem: DataItem){
        ghibli = dataItem
    }
    private val _status: MutableLiveData<Boolean> = MutableLiveData(false)

    val status: LiveData<Boolean> = _status

    fun setStatus(value: Boolean) {
        _status.value = value
    }
}

