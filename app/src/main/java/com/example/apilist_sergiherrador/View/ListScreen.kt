package com.example.apilist_sergiherrador.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
    apiViewModel.getCharacters()
    if (showLoading) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary
        )
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.studioghibli),
                contentDescription = "logo",
                modifier = Modifier
                    .fillMaxHeight(0.3f)
                    .padding(16.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(1f)
            ) {
                TextField(
                    value = searchText,
                    onValueChange = { apiViewModel.onSearchTextChange(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    label = { Text(text = "Search") }
                )

                LazyColumn(
                    modifier = Modifier.fillMaxHeight(0.9f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    val filteredCharacters = characters.filter { ghibli ->
                        ghibli.title.contains(searchText) || ghibli.original_title.contains(
                            searchText
                        )
                    }
                    items(filteredCharacters) { ghibli ->
                        GhibliItem(
                            ghibli = ghibli,
                            navController = navigationController,
                            listScreenViewModel
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = "Favs")
                    Text(text = "All")
                }
                Button(onClick = {
                    navigationController.navigate(Routes.DetailScreen.route)
                }) {
                    Text(text = "PERU")
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