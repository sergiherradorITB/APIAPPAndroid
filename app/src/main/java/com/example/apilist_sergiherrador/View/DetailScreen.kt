package com.example.apilist_sergiherrador.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apilist_sergiherrador.Model.DataItem
import com.example.apilist_sergiherrador.Model.PersonaItem
import com.example.apilist_sergiherrador.R
import com.example.apilist_sergiherrador.Routes
import com.example.apilist_sergiherrador.ViewModel.APIViewModel
import com.example.apilist_sergiherrador.ViewModel.ListScreenViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(
    navController: NavController,
    listScreenViewModel: ListScreenViewModel,
    apiViewModel: APIViewModel
) {
    val characters: List<PersonaItem> by apiViewModel.people.observeAsState(emptyList<PersonaItem>())
    apiViewModel.getPeople()
    Column(modifier = Modifier.fillMaxSize()) {
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
                // Imagen arriba
                GlideImage(
                    model = listScreenViewModel.pillarGhibli().image,
                    contentDescription = "Character Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize().padding(bottom = 10.dp)
                )
                IconButton(
                    onClick = { /* TODO Lógica de añadir a favoritos */ },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Añadir a favoritos"
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
                // Título grande
                Text(
                    text = listScreenViewModel.pillarGhibli().title,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 33.sp
                )

                // Descripción
                Text(
                    text = listScreenViewModel.pillarGhibli().description,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )

                Button(onClick = {
                    navController.navigate(Routes.ListScreen.route)
                }) {
                    Text(text = "Volver al menú")
                }
                // Mostrar detalles de personajes en un LazyColumn
                Text(
                    text = if (listScreenViewModel.pillarGhibli().people[0] == "https://ghibliapi.vercel.app/people/")
                        "No personatges trobat a la API"
                    else
                        "Pesonatges: ",
                    modifier = Modifier.padding(top = 8.dp)
                )
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    val filteredCharacters = characters.filter { character ->
                        val moviePeopleUrls = listScreenViewModel.pillarGhibli().people
                        moviePeopleUrls.any { it.contains(character.id) }
                    }

                    items(filteredCharacters) { character ->
                        personaItem(character, navController, listScreenViewModel)
                    }
                }
            }
        }
        MyBottomBar(
            navController = navController,
            listScreenViewModel = listScreenViewModel
        )
    }
}