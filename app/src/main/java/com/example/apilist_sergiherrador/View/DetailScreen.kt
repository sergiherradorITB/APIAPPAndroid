package com.example.apilist_sergiherrador.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.apilist_sergiherrador.Model.DataItem
import com.example.apilist_sergiherrador.R
import com.example.apilist_sergiherrador.Routes
import com.example.apilist_sergiherrador.ViewModel.ListScreenViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(navController: NavController, listScreenViewModel: ListScreenViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        // verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen arriba
        GlideImage(
            model = listScreenViewModel.ghibli.image,
            contentDescription = "Character Image",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(0.4f)
        )

        // Título grande
        Text(
            text = listScreenViewModel.ghibli.title,
            modifier = Modifier.fillMaxWidth(),
        )

        // Descripción
        Text(
            text = listScreenViewModel.ghibli.description,
            modifier = Modifier.fillMaxWidth(),
            maxLines = 5,
            overflow = TextOverflow.Ellipsis
        )

        Button(onClick = {
            navController.navigate(Routes.ListScreen.route)
        }) {
            Text(text = "Volver al menú")
        }
    }
}
