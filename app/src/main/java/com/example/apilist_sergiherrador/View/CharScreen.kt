package com.example.apilist_sergiherrador.View

import Colores
import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.apilist_sergiherrador.Model.PersonaItem
import com.example.apilist_sergiherrador.Routes
import com.example.apilist_sergiherrador.ViewModel.APIViewModel
import com.example.apilist_sergiherrador.ViewModel.ListDetailScreenViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharScreen(
    navigationController: NavController,
    apiViewModel: APIViewModel,
    listScreenViewModel: ListDetailScreenViewModel
) {
    val showLoading: Boolean by apiViewModel.loading.observeAsState(true)
    val characters: List<PersonaItem> by apiViewModel.people.observeAsState(emptyList<PersonaItem>())
    val searchText: String by apiViewModel.searchText.observeAsState("")
    val searchStatus: Boolean by listScreenViewModel.status.observeAsState(false)

    apiViewModel.getPeople()

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
                        Text(
                            text = "SERGHI-BLI",
                            textDecoration = TextDecoration.Underline,
                            fontFamily = FontFamily.SansSerif,
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navigationController.navigate(Routes.DetailScreen.route)
                            }, enabled = listScreenViewModel.pillarGhibli().title != ""
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = if (listScreenViewModel.pillarGhibli().title != "") Color.White else Color.Black
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { listScreenViewModel.setStatus(!searchStatus) }) {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = "Search",
                                tint = Color.White
                            )
                        }
                    }
                )
            },
            bottomBar = {
                MyBottomBar(
                    navController = navigationController,
                    listScreenViewModel = listScreenViewModel
                )
            }
        ) { paddingValues ->
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Colores.Purpura.color)
                        .padding(paddingValues)
                ) {
                    if (searchStatus) {
                        busqueda(searchText, apiViewModel)
                    }
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        val filteredCharacters = characters.filter { persona ->
                            persona.name.lowercase().contains(searchText)
                        }
                        items(filteredCharacters) { persona ->
                            personaItem(
                                persona = persona,
                                navController = navigationController,
                                listScreenViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun personaItem(
    persona: PersonaItem,
    navController: NavController,
    listScreenViewModel: ListDetailScreenViewModel
) {
    Card(border = BorderStroke(2.dp, Color.LightGray), modifier = Modifier.fillMaxWidth()) {
        Column {
            Row {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = persona.name)
                    Text(text = persona.age)
                }
            }
        }
    }
}