package com.example.apilist_sergiherrador.View

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.apilist_sergiherrador.Model.GhibliFilm
import com.example.apilist_sergiherrador.R

@Composable
fun ListScreen() {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        items(getGhibliList()) {
            GhibliItem(ghibli = it)
        }
    }
}

fun getGhibliList(): List<GhibliFilm> {
    return listOf(
        GhibliFilm("GHI001", "El viaje de Chihiro", R.drawable.viaje_chihiro, "20/06/01"),
        GhibliFilm("GHI002", "La princesa Mononoke", R.drawable.princesa_mononoke, "20/11/03"),
        GhibliFilm("GHI003", "Mi vecino Totoro", R.drawable.vecino_totoro, "21/04/16"),
        GhibliFilm("GHI004", "El castillo ambulante", R.drawable.castillo_ambulante, "21/08/07"),
        GhibliFilm("GHI005", "Nausicaä del Valle del Viento", R.drawable.nausicaa, "22/02/22"),
        GhibliFilm("GHI006", "El castillo en el cielo", R.drawable.castillo_cielo, "22/06/30"),
        GhibliFilm("GHI007", "El viento se levanta", R.drawable.viento_levanta, "23/01/21"),
        GhibliFilm("GHI008", "El recuerdo de Marnie", R.drawable.recuerdo_marnie, "23/07/19"),
        GhibliFilm("GHI009", "Ponyo en el acantilado", R.drawable.ponyo, "24/03/02"),
        GhibliFilm("GHI010", "El cuento de la princesa Kaguya", R.drawable.princesa_kaguya, "24/11/08")
    )
}

@Composable
fun GhibliItem(ghibli: GhibliFilm) {
    Card(border = BorderStroke(2.dp, Color.LightGray), modifier = Modifier.fillMaxWidth()) {
        Row {
            Image(
                painter = painterResource(id = ghibli.image),
                contentDescription = ghibli.title,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.4f)
            )
            Column {
                Text(text = ghibli.title)
                Text(text = ghibli.releaseDate)
            }
        }
    }
}