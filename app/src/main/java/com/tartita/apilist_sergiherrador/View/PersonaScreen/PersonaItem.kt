package com.tartita.apilist_sergiherrador.View.PersonaScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.tartita.apilist_sergiherrador.Model.PersonaItem
import com.tartita.apilist_sergiherrador.ViewModel.ListDetailScreenViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun personaItem(
    persona: PersonaItem,
    navController: NavController,
    listScreenViewModel: ListDetailScreenViewModel
) {
    Card(border = BorderStroke(2.dp, Color.LightGray), modifier = Modifier.fillMaxWidth()) {
        Column {
            Row {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = persona.name)
                    Text(text = persona.age)
                }
            }
        }
    }
}