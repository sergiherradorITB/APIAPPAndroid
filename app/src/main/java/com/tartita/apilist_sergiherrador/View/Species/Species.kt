package com.tartita.apilist_sergiherrador.View.Species

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import com.tartita.apilist_sergiherrador.Model.Species
import com.tartita.apilist_sergiherrador.View.Charging
import com.tartita.apilist_sergiherrador.View.MyBottomBar
import com.tartita.apilist_sergiherrador.View.MySearchBar
import com.tartita.apilist_sergiherrador.ViewModel.APIViewModel
import com.tartita.apilist_sergiherrador.ViewModel.ListDetailScreenViewModel
import com.tartita.apilist_sergiherrador.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Species(
    navigationController: NavController,
    apiViewModel: APIViewModel,
    listScreenViewModel: ListDetailScreenViewModel
) {
    val showLoading: Boolean by apiViewModel.loadingFilm2.observeAsState(true)
    val species: Species by apiViewModel.species.observeAsState(Species())
    val searchText: String by apiViewModel.searchText.observeAsState("")
    val searchStatus: Boolean by listScreenViewModel.status.observeAsState(false)
    apiViewModel.getSpecies()

    if (showLoading) {
        Charging()
    } else {
        val context = LocalContext.current

        val fontFamily = remember {
            FontFamily(
                typeface = ResourcesCompat.getFont(context, R.font.mogilte)!!
            )
        }

        Scaffold(
            topBar = { SpeciesTopBar(fontFamily, navigationController, listScreenViewModel, searchStatus) },
            bottomBar = { MyBottomBar(navController = navigationController,) }
        ) { paddingValues ->
            SpeciesContent(paddingValues, searchStatus, searchText, apiViewModel, species, navigationController, listScreenViewModel)
        }
    }
}

@Composable
private fun SpeciesContent(
    paddingValues: PaddingValues,
    searchStatus: Boolean,
    searchText: String,
    apiViewModel: APIViewModel,
    species: Species,
    navigationController: NavController,
    listScreenViewModel: ListDetailScreenViewModel
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Colores.Lila.color)
                .padding(paddingValues)
        ) {
            if (searchStatus) {
                MySearchBar(apiViewModel)
            }
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(1f),
                columns = GridCells.Fixed(2),
                content = {
                    val filteredCharacters = species.filter { species ->
                        species.name.lowercase().contains(searchText)
                    }
                    items(filteredCharacters) { species ->
                        speciesItem(
                            species = species,
                            navController = navigationController,
                            listScreenViewModel
                        )
                    }
                }
            )
        }
    }
}