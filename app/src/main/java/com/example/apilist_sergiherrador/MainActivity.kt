package com.example.apilist_sergiherrador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apilist_sergiherrador.View.*
import com.example.apilist_sergiherrador.View.FavoriteScreen.FavoritesScreen
import com.example.apilist_sergiherrador.View.ListScreen.DetailScreen
import com.example.apilist_sergiherrador.View.Locations.Location
import com.example.apilist_sergiherrador.View.Locations.LocationDetailScreen
import com.example.apilist_sergiherrador.View.PersonaScreen.CharScreen
import com.example.apilist_sergiherrador.View.Species.Species
import com.example.apilist_sergiherrador.View.Species.SpeciesDetailScreen
import com.example.apilist_sergiherrador.ViewModel.APIViewModel
import com.example.apilist_sergiherrador.ViewModel.ListDetailScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navigationController = rememberNavController()
            val apiViewModel by viewModels<APIViewModel>()
            val listScreenViewModel by viewModels<ListDetailScreenViewModel>()
            NavHost(
                navController = navigationController,
                startDestination = Routes.ListScreen.route
            ){
                composable(Routes.ListScreen.route){
                    ListScreen(navigationController, apiViewModel, listScreenViewModel)
                }
                composable(Routes.DetailScreen.route){
                    DetailScreen(navigationController, listScreenViewModel, apiViewModel)
                }
                composable(Routes.CharScreen.route){
                    CharScreen(navigationController, apiViewModel, listScreenViewModel)
                }
                composable(Routes.Species.route){
                    Species(navigationController, apiViewModel, listScreenViewModel)
                }
                composable(Routes.SpeciesDetail.route){
                    SpeciesDetailScreen(navigationController,listScreenViewModel, apiViewModel)
                }
                composable(Routes.Location.route){
                    Location(navigationController,apiViewModel,listScreenViewModel)
                }
                composable(Routes.LocationDetail.route){
                    LocationDetailScreen(navigationController,listScreenViewModel,apiViewModel)
                }
                composable(Routes.Favorite.route){
                    FavoritesScreen(navigationController,apiViewModel,listScreenViewModel)
                }
            }
        }
    }
}