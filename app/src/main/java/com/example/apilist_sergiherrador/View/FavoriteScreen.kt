package com.example.apilist_sergiherrador.View

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import com.example.apilist_sergiherrador.Model.DetailFilmItem
import com.example.apilist_sergiherrador.ViewModel.APIViewModel
import com.example.apilist_sergiherrador.ViewModel.ListDetailScreenViewModel
import com.example.apilist_sergiherrador.Model.*
import com.example.apilist_sergiherrador.R
import com.example.apilist_sergiherrador.Routes

@Composable
fun FavoritesScreen(
    navigationController: NavController,
    apiViewModel: APIViewModel,
    listScreenViewModel: ListDetailScreenViewModel
) {
    val showLoading: Boolean by apiViewModel.loading.observeAsState(true)
    val favorites: List<DetailFilmItem> by apiViewModel.favorites.observeAsState(emptyList())
    val searchText: String by apiViewModel.searchText.observeAsState("")
    val searchStatus: Boolean by listScreenViewModel.status.observeAsState(false)
    apiViewModel.getFavorites()

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
                                navigationController.navigate(Routes.DetailScreen.route)
                            }, enabled = listScreenViewModel.pillarGhibliId() != ""
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = if (listScreenViewModel.pillarGhibliId() != "") Color.White else Color.Black
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
                        .background(Colores.Lila.color)
                        .padding(paddingValues)
                ) {

                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        val filteredCharacters = favorites.filter { ghibli ->
                            ghibli.title.lowercase()
                                .contains(searchText) || ghibli.original_title.contains(
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
                }
            }
        }
    }
}
