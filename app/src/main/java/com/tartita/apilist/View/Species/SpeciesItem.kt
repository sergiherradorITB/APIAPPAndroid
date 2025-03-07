package com.tartita.apilist.View.Species

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.tartita.apilist.Model.SpeciesItem
import com.tartita.apilist.Routes
import com.tartita.apilist.ViewModel.ListDetailScreenViewModel
import com.tartita.apilist.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun speciesItem(
    species: SpeciesItem,
    navController: NavController,
    listScreenViewModel: ListDetailScreenViewModel
) {
    Card(
        border = BorderStroke(2.dp, Color.LightGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .clickable {
                listScreenViewModel.modificarSpecie(species)
                navController.navigate(Routes.SpeciesDetail.route)
            }
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.studioghibli),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.4f) // Ajusta la transparencia de la imagen
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Center)
            ) {
                Text(
                    text = species.name,
                    modifier = Modifier.padding(bottom = 8.dp),
                    fontSize = 18.sp, // Tamaño de fuente más grande
                    fontWeight = FontWeight.Bold, // Texto remarcado
                    textAlign = TextAlign.Center // Alineación central
                )
            }
        }
    }
}