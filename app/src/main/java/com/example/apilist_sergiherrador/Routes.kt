package com.example.apilist_sergiherrador

sealed class Routes(val route:String) {
    object ListScreen:Routes("listscreen")
    object DetailScreen:Routes("detailscreen")


    object CharScreen:Routes("charscreen")


    object Species:Routes("species")
    object SpeciesDetail:Routes("species_detail")


    object Location:Routes("locations")
    object LocationDetail:Routes("locations_detail")



}