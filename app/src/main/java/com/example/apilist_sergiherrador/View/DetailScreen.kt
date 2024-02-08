package com.example.apilist_sergiherrador.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.apilist_sergiherrador.Model.DataItem
import com.example.apilist_sergiherrador.R
import com.example.apilist_sergiherrador.ViewModel.ListScreenViewModel

@Composable
fun DetailScreen(navController: NavController, listScreenViewModel: ListScreenViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Imagen arriba
        Image(
            painter = painterResource(id = R.drawable.castillo_ambulante),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
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
    }
}
