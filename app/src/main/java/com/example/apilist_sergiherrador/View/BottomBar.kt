package com.example.apilist_sergiherrador.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.apilist_sergiherrador.Routes
import com.example.apilist_sergiherrador.ViewModel.ListScreenViewModel
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun MyBottomBar(
    navController: NavController,
    listScreenViewModel: ListScreenViewModel
) {
    BottomNavigation(
        backgroundColor = Colores.Purpura.color,
        contentColor = Color.White,
    ) {
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.ArrowBack, contentDescription = "Back") },
            // label = { Text("Back") },
            // selected = listScreenViewModel.pillarGhibli().title == "",
            // No me va lo de selected lol lselected = false,
            selected = false,
            enabled = listScreenViewModel.pillarGhibli().title != "",
            onClick = { navController.navigate(Routes.DetailScreen.route) }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = Color.White
                )
            },
            // label = { Text("Favorite") },
            selected = false,
            onClick = { /*TODO*/ }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "List",
                    tint = Color.White
                )
            },
            // label = { Text("Lista Pelis") },
            selected = false,
            onClick = { navController.navigate(Routes.ListScreen.route) }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = "List",
                    tint = Color.White
                )
            },
            // label = { Text("Lista Personajes") },
            selected = true,
            onClick = { navController.navigate(Routes.CharScreen.route) }
        )
    }
}

/*
    OLD BOTTOM:

    @Composable
fun Bottom(navigationController: NavController, listScreenViewModel: ListScreenViewModel) {
    Row(
        modifier = Modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                navigationController.navigate(Routes.DetailScreen.route)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Colores.Lila.color // Set the text/icon color of the button
            ), enabled = listScreenViewModel.pillarGhibli().title != ""
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }
        Button(
            onClick = {
                navigationController.navigate(Routes.DetailScreen.route)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Colores.Lila.color // Set the text/icon color of the button
            )
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite",
                tint = Color.White
            )
        }
        Button(
            onClick = {
                navigationController.navigate(Routes.ListScreen.route)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Colores.Lila.color // Set the text/icon color of the button
            )

        ) {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = "List",
                tint = Color.White
            )
        }
        Button(
            onClick = {
                navigationController.navigate(Routes.CharScreen.route)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Colores.Lila.color // Set the text/icon color of the button
            )

        ) {
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = "List",
                tint = Color.White
            )
        }
    }
}

 */