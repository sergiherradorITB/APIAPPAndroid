package com.example.apilist_sergiherrador.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.apilist_sergiherrador.Model.DataItem

class ListScreenViewModel : ViewModel() {
    var ghibli: DataItem by mutableStateOf(DataItem("", "", "", "", listOf(), "", "", "", listOf(), "", "", "", "", listOf(), "", "", listOf()))
        private set

    fun pillarGhibli():DataItem{
        return ghibli
    }

    fun modificarGhibli(dataItem: DataItem){
        ghibli = dataItem
    }
}

