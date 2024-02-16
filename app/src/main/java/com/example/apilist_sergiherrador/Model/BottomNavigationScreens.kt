package com.example.apilist_sergiherrador.Model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.apilist_sergiherrador.Routes


sealed class BottomNavigationScreens (val route:String, val icon: ImageVector, val label:String){
    object Home:BottomNavigationScreens(Routes.ListScreen.route, Icons.Filled.Home, "Home")
    object Favorite:BottomNavigationScreens(Routes.DetailScreen.route, Icons.Filled.Favorite, "Favorite")
    object CharScreen:BottomNavigationScreens(Routes.CharScreen.route, Icons.Filled.Face, "Face")
}