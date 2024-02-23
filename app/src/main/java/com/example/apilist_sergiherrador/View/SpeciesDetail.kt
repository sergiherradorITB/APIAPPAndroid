package com.example.apilist_sergiherrador.View

import com.example.apilist_sergiherrador.Model.AllFilms
import com.example.apilist_sergiherrador.Model.SpeciesItem
import com.example.apilist_sergiherrador.Model.PersonaItem
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apilist_sergiherrador.Model.DetailFilmItem
import com.example.apilist_sergiherrador.R
import com.example.apilist_sergiherrador.Routes
import com.example.apilist_sergiherrador.ViewModel.APIViewModel
import com.example.apilist_sergiherrador.ViewModel.ListDetailScreenViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SpeciesDetailScreen(
    navController: NavController,
    listScreenViewModel: ListDetailScreenViewModel,
    apiViewModel: APIViewModel
) {
    val oneSpecieDetailed: SpeciesItem by apiViewModel.speciesItemDetailed.observeAsState(
        listScreenViewModel.pillarSpecie()
    )
    val characters: List<PersonaItem> by apiViewModel.people.observeAsState(emptyList<PersonaItem>())
    val films: List<AllFilms> by apiViewModel.films.observeAsState(emptyList<AllFilms>())
    val showLoading: Boolean by apiViewModel.loading.observeAsState(true)
    val searchStatus: Boolean by listScreenViewModel.status.observeAsState(false)

    apiViewModel.getSpeciesDetailed(listScreenViewModel.pillarSpecie().id)
    apiViewModel.getFilms()
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
        val context = LocalContext.current

        val fontFamily = remember {
            FontFamily(
                typeface = ResourcesCompat.getFont(context, R.font.mogilte)!!
            )
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = Colores.Purpura.color,
                    title = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "SERGIBLI ©",
                                modifier = Modifier.weight(1f),
                                style = TextStyle(fontFamily = fontFamily),
                                color = Color.White,
                                fontSize = 23.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.navigate(Routes.Species.route)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                    },
                    actions = {
                        // Aquí llamamos a la función de compartir
                        ShareButton(
                            text = "Hola, mira esta especie: ${oneSpecieDetailed.name}\nTiene los ojos ${oneSpecieDetailed.eye_colors}\nEs una pasada! ${oneSpecieDetailed.url}",
                            context = context,
                        )
                    }
                )
            },
            bottomBar = {
                MyBottomBar(
                    navController = navController,
                    listScreenViewModel = listScreenViewModel
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Colores.Lila.color)
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
            ) {
                Box(
                    modifier = Modifier
                        .weight(0.4f)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Título grande
                        Text(
                            text = oneSpecieDetailed.name,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 33.sp
                        )
                        // Descripción
                        Text(
                            text = "Eye colors: ${oneSpecieDetailed.eye_colors}",
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 5,
                            overflow = TextOverflow.Ellipsis
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 9.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = "Classification: ${oneSpecieDetailed.classification}")
                                Text(text = "Hair Colors: ${oneSpecieDetailed.hair_colors}")
                            }
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 8.dp) // Ajuste de los márgenes y espacios de relleno
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val PeopleUrls = oneSpecieDetailed.people
                        val FilmsUrls = oneSpecieDetailed.films

                        Text(
                            text = "Films that ${oneSpecieDetailed.name} appears: ",
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(vertical = 8.dp)
                        ) {
                            LazyColumn(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                val filteredFilms = films.filter { film ->
                                    FilmsUrls.any { it.contains(film.id) }
                                }

                                items(filteredFilms) { film ->
                                    GhibliItem(film, navController, listScreenViewModel)
                                }
                            }
                        }
                        Text(
                            text = if (PeopleUrls[0] == "https://ghibliapi.vercel.app/people/")
                                "No personatges trobats a l'API"
                            else
                                "Characters that are ${oneSpecieDetailed.name}: ",
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(vertical = 8.dp)
                                .border(
                                    border = if (PeopleUrls[0] == "https://ghibliapi.vercel.app/people/")
                                        BorderStroke(0.dp, Color.Transparent)
                                    else
                                        BorderStroke(2.dp, Color.LightGray)
                                )
                        ) {
                            LazyColumn(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                val filteredCharacters = characters.filter { character ->
                                    PeopleUrls.any { it.contains(character.id) }
                                }

                                items(filteredCharacters) { character ->
                                    personaItem(character, navController, listScreenViewModel)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
