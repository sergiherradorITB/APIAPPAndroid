package com.example.apilist_sergiherrador

import com.example.apilist_sergiherrador.Model.DataItem

sealed class Routes(val route:String) {
    object ListScreen:Routes("listscreen")
    object DetailScreen:Routes("detailscreen")
    object CharScreen:Routes("charscreen")

}