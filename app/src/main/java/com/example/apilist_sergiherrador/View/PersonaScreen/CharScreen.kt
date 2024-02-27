package com.example.apilist_sergiherrador.View.PersonaScreen

import Colores
import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.apilist_sergiherrador.Model.PersonaItem
import com.example.apilist_sergiherrador.R
import com.example.apilist_sergiherrador.Routes
import com.example.apilist_sergiherrador.View.MyBottomBar
import com.example.apilist_sergiherrador.ViewModel.APIViewModel
import com.example.apilist_sergiherrador.ViewModel.ListDetailScreenViewModel
import com.example.apilist_sergiherrador.View.Charging
import com.example.apilist_sergiherrador.View.MySearchBar


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
        Charging()
    } else {
        val context = LocalContext.current

        val fontFamily = remember {
            FontFamily(
                typeface = ResourcesCompat.getFont(context, R.font.mogilte)!!
            )
        }
        Scaffold(
            topBar = {
                CharTopAppBar(
                    fontFamily,
                    navigationController,
                    listScreenViewModel,
                    searchStatus
                )
            },
            bottomBar = { MyBottomBar(navController = navigationController) }
        ) { paddingValues ->
            CharScreenContent(
                paddingValues,
                searchStatus,
                searchText,
                apiViewModel,
                characters,
                navigationController,
                listScreenViewModel
            )
        }
    }
}

@Composable
private fun CharScreenContent(
    paddingValues: PaddingValues,
    searchStatus: Boolean,
    searchText: String,
    apiViewModel: APIViewModel,
    characters: List<PersonaItem>,
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