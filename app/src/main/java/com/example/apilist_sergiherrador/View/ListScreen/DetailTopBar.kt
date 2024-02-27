package com.example.apilist_sergiherrador.View.ListScreen

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.apilist_sergiherrador.Model.DetailFilmItem
import com.example.apilist_sergiherrador.Routes
import com.example.apilist_sergiherrador.ViewModel.ListDetailScreenViewModel


@Composable
fun DetailTopBar(
    fontFamily: FontFamily,
    navController: NavController,
    listScreenViewModel: ListDetailScreenViewModel,
    oneFilmDetailed: DetailFilmItem,
    context: Context
) {
    TopAppBar(
        backgroundColor = Colores.Purpura.color,
        title = {
            Text(
                text = "SERGIBLI ©",
                style = TextStyle(fontFamily = fontFamily),
                color = Color.White,
                fontSize = 23.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.navigate(Routes.ListScreen.route)
                },
                enabled = listScreenViewModel.pillarGhibliId() != ""
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = if (listScreenViewModel.pillarGhibliId() != "") Color.White else Color.Black
                )
            }
        },
        actions = {
            // Aquí llamamos a la función de compartir
            ShareButton(
                text = "Hola, mira esta peli: ${oneFilmDetailed.title}\nTiene una nota de: ${oneFilmDetailed.rt_score}\nEs una pasada! ${oneFilmDetailed.url}",
                context = context,
            )
        }
    )
}
