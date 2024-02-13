package com.example.apilist_sergiherrador.View

import Colores
import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apilist_sergiherrador.Model.DataItem
import com.example.apilist_sergiherrador.R
import com.example.apilist_sergiherrador.Routes
import com.example.apilist_sergiherrador.ViewModel.APIViewModel
import com.example.apilist_sergiherrador.ViewModel.ListScreenViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    navigationController: NavController,
    apiViewModel: APIViewModel,
    listScreenViewModel: ListScreenViewModel
) {
    val showLoading: Boolean by apiViewModel.loading.observeAsState(true)
    val characters: List<DataItem> by apiViewModel.films.observeAsState(emptyList<DataItem>())
    val searchText: String by apiViewModel.searchText.observeAsState("")
    val searchStatus: Boolean by listScreenViewModel.status.observeAsState(false)

    apiViewModel.getFilms()

    if (showLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colorScheme.secondary
            )
        }
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "SerGhibli")
                    },
                    navigationIcon = {
                        IconButton(onClick = { listScreenViewModel.setStatus(!searchStatus) }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    },
                    actions = {
                        IconButton(onClick = { listScreenViewModel.setStatus(!searchStatus) }) {
                            Icon(Icons.Default.Search, contentDescription = "Search")
                        }
                    }
                )
            }
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Colores.Purpura.color)
                ) {
                    if (searchStatus) {
                        TextField(
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search",
                                    tint = Color.Gray
                                )
                            },
                            value = searchText,
                            onValueChange = { apiViewModel.onSearchTextChange(it.lowercase()) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp, bottom = 5.dp),
                            label = {
                                Text(text = "Search")
                            }
                        )
                    }
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        val filteredCharacters = characters.filter { ghibli ->
                            ghibli.title.lowercase().contains(searchText) || ghibli.original_title.contains(
                                searchText
                            )
                        }
                        items(filteredCharacters) {
                            GhibliItem(
                                ghibli = it,
                                navController = navigationController,
                                listScreenViewModel
                            )
                        }
                    }
                    MyBottomBar(navigationController, listScreenViewModel)
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GhibliItem(
    ghibli: DataItem,
    navController: NavController,
    listScreenViewModel: ListScreenViewModel
) {
    Card(border = BorderStroke(2.dp, Color.LightGray), modifier = Modifier.fillMaxWidth()) {
        Column {
            Row {
                // Imagen arriba
                GlideImage(
                    model = ghibli.image,
                    contentDescription = "Character Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(100.dp)
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    // verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = ghibli.title)
                    Text(text = ghibli.original_title)

                    Button(onClick = {
                        listScreenViewModel.modificarGhibli(ghibli)
                        navController.navigate(Routes.DetailScreen.route)
                    }) {
                        Text(text = "Ver detalles")
                    }
                }
            }
        }
    }
}