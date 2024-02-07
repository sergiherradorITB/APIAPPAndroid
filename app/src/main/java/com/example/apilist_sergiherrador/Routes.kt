package com.example.apilist_sergiherrador

sealed class Routes(val route:String) {
    object ListScreen:Routes("listscreen")
}