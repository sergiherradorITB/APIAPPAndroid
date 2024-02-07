package com.example.apilist_sergiherrador

sealed class Routes(val route:String) {
    object ListScreen:Routes("listscreen")
    object DetailScreen:Routes("detailscreen/{ghibli}"){
        fun createRoute(ghibli:String) = "detailscreen/$ghibli"
    }


}