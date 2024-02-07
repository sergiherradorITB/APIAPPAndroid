package com.example.apilist_sergiherrador.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apilist_sergiherrador.Model.DataItem
import com.example.apilist_sergiherrador.Routes
import com.example.apilist_sergiherrador.ViewModel.APIViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavController, apiViewModel: APIViewModel) {
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
                    ghibli.title.contains(searchText) || ghibli.original_title.contains(searchText)
                }
                items(filteredCharacters) { ghibli ->
                    GhibliItem(ghibli = ghibli, navController = navController)
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
        }
    }
}

@Composable
fun GhibliItem(ghibli: DataItem, navController: NavController) {
    Card(border = BorderStroke(2.dp, Color.LightGray), modifier = Modifier.fillMaxWidth()) {
        Column {
            Text(text = ghibli.title)
            Text(text = ghibli.original_title)
            Button(onClick = {
                navController.navigate(Routes.DetailScreen.createRoute(ghibli))
            }) {
                Text(text = "Ver detalles")
            }
        }
    }
}