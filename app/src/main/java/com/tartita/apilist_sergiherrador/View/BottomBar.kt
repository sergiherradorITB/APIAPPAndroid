package com.tartita.apilist_sergiherrador.View

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.tartita.apilist_sergiherrador.Model.BottomNavigationScreens

val bottomNavigationItems = listOf<BottomNavigationScreens>(
    BottomNavigationScreens.Home,
    BottomNavigationScreens.Favorite,
    BottomNavigationScreens.CharScreen,
    BottomNavigationScreens.Species,
    BottomNavigationScreens.Location
)

@Composable
fun MyBottomBar(
    navController: NavController,
) {
    BottomNavigation(
        backgroundColor = Colores.Purpura.color,
        contentColor = Color.White,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        bottomNavigationItems.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.label, tint = if (currentRoute == item.route) Color.White else Colores.PurpuraFuerte.color) },
                label = { Text(item.label, color = if (currentRoute == item.route) Color.White else Color.Black) },
                selected = currentRoute == item.route,
                selectedContentColor = Colores.Purpura.color,
                unselectedContentColor = Color.Transparent,
                alwaysShowLabel = false,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route)
                    }
                }

            )
        }
    }
}