package com.example.apilist_sergiherrador.View

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
import androidx.compose.material.icons.filled.FavoriteBorder
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
import com.example.apilist_sergiherrador.Model.PersonaItem
import com.example.apilist_sergiherrador.R
import com.example.apilist_sergiherrador.Routes
import com.example.apilist_sergiherrador.ViewModel.APIViewModel
import com.example.apilist_sergiherrador.ViewModel.ListDetailScreenViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(
    navController: NavController,
    listScreenViewModel: ListDetailScreenViewModel,
    apiViewModel: APIViewModel
) {
    val characters: List<PersonaItem> by apiViewModel.people.observeAsState(emptyList())
    val oneFilmDetailed: DetailFilmItem by apiViewModel.detailFilm.observeAsState(
        DetailFilmItem(
            "",
            "",
            "",
            "",
            listOf(),
            "",
            "",
            "",
            listOf(),
            "",
            "",
            "",
            "",
            listOf(),
            "",
            "",
            listOf()
        )
    )
    val showLoading: Boolean by apiViewModel.loadingFilm.observeAsState(true)
    val isFavorite by apiViewModel.isFavorite.observeAsState(initial = false)
    apiViewModel.getPeople()
    apiViewModel.getOneFilm(listScreenViewModel.pillarGhibliId())
    apiViewModel.isFavorite(oneFilmDetailed)

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
                                navController.navigate(Routes.ListScreen.route)
                            }, enabled = listScreenViewModel.pillarGhibliId() != ""
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = if (listScreenViewModel.pillarGhibliId() != "") Color.White else Color.Black
                            )
                        }
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
                    .padding(paddingValues)
                    .background(Colores.Lila.color)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier
                        .weight(0.8f)
                        .padding(top = 18.dp, start = 10.dp, end = 10.dp)
                        .fillMaxWidth(),
                ) {
                    Box(
                        modifier = Modifier
                            .weight(0.4f)
                            .fillMaxWidth()
                    ) {
                        Column {
                            IconButton(
                                onClick = {
                                    if (isFavorite) {
                                        apiViewModel.deleteFavorite(oneFilmDetailed)
                                    } else {
                                        apiViewModel.saveAsFavorite(oneFilmDetailed)
                                    }
                                }
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.End),
                                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                    contentDescription = "Añadir a favoritos"
                                )
                            }
                            GlideImage(
                                model = oneFilmDetailed.movie_banner,
                                contentDescription = "Character Image",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(bottom = 10.dp)
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .weight(0.6f)
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Column(
                            modifier = Modifier.background(Color.White.copy(alpha = 0.2f)) // Fondo semitransparente
                        ) {

                            // Título grande
                            Text(
                                text = oneFilmDetailed.title,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontSize = 33.sp
                            )

                            // Descripción
                            Text(
                                text = oneFilmDetailed.description,
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

                                    Text(text = "Nota: ${oneFilmDetailed.rt_score}")
                                    Text(text = "Director: ${oneFilmDetailed.director}")
                                    Text(text = "Año de Salida: ${oneFilmDetailed.release_date}")
                                }
                                Column(modifier = Modifier.weight(1f)) {
                                    Button(onClick = {
                                        listScreenViewModel.modificarShow(true)
                                    }) {
                                        Text(text = "Mostrar más")
                                    }
                                }
                            }
                            MyDialog(
                                show = listScreenViewModel.pillarShow(),
                                onDismiss = { listScreenViewModel.modificarShow(false) },
                                filmItem = oneFilmDetailed
                            )
                        }
                        val moviePeopleUrls = oneFilmDetailed.people
                        Text(
                            text = if (moviePeopleUrls[0] == "https://ghibliapi.vercel.app/people/")
                                "No personatges trobats a l'API"
                            else
                                "Pesonatges: ",
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        LazyColumn(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .border(
                                    border =
                                    if (moviePeopleUrls[0] == "https://ghibliapi.vercel.app/people/") {
                                        BorderStroke(0.dp, Color.Transparent)
                                    } else {
                                        BorderStroke(2.dp, Color.LightGray)
                                    }
                                )
                        ) {
                            val filteredCharacters = characters.filter { character ->
                                moviePeopleUrls.any { it.contains(character.id) }
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

@Composable
fun MyDialog(show: Boolean, onDismiss: () -> Unit, filmItem: DetailFilmItem) {
    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .background(Color(0xFF85a2b6)) // Color de fondo de Totoro
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Información detallada",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "Título original: ${filmItem.original_title}",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Director: ${filmItem.director}",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Fecha de lanzamiento: ${filmItem.release_date}",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Puntuación: ${filmItem.rt_score}/100",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Descripción: ${filmItem.description}",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
}