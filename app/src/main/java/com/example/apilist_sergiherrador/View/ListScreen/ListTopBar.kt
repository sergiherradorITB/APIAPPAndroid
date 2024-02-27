package com.example.apilist_sergiherrador.View.ListScreen



import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apilist_sergiherrador.Model.AllFilms
import com.example.apilist_sergiherrador.Model.DetailFilmItem
import com.example.apilist_sergiherrador.R
import com.example.apilist_sergiherrador.Routes
import com.example.apilist_sergiherrador.View.MyBottomBar
import com.example.apilist_sergiherrador.ViewModel.APIViewModel
import com.example.apilist_sergiherrador.ViewModel.ListDetailScreenViewModel

@Composable
fun ListTopAppBar(
    fontFamily: FontFamily,
    navigationController: NavController,
    listScreenViewModel: ListDetailScreenViewModel,
    searchStatus: Boolean
) {
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
}