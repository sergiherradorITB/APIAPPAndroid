package com.tartita.apilist.View.PersonaScreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tartita.apilist.Routes
import com.tartita.apilist.ViewModel.ListDetailScreenViewModel

@Composable
fun CharTopAppBar(
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