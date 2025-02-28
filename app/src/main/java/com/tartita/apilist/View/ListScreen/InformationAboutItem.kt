package com.tartita.apilist.View.ListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import com.tartita.apilist.Model.DetailFilmItem
import com.tartita.apilist.ViewModel.ListDetailScreenViewModel
import com.tartita.apilist.R

@Composable
fun InformationAboutItem(
    oneFilmDetailed: DetailFilmItem,
    listScreenViewModel: ListDetailScreenViewModel
) {
    Column(
        modifier = Modifier
            .background(Color.White.copy(alpha = 0.3f))
            .padding(2.dp) // Fondo semitransparente
    ) {
        val context = LocalContext.current

        val fontFamily = remember {
            FontFamily(
                typeface = ResourcesCompat.getFont(context, R.font.novirus)!!
            )
        }
        // Título grande
        Text(
            text = oneFilmDetailed.title,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = TextStyle(fontFamily = fontFamily),
            fontSize = 33.sp
        )

        // Descripción
        Text(
            text = oneFilmDetailed.description,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            maxLines = 5,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Justify
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 9.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row {
                    Text(
                        text = "Nota: ",
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = oneFilmDetailed.rt_score,
                    )
                }
                Row {
                    Text(
                        text = "Director: ",
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = oneFilmDetailed.director,
                    )
                }
                Row {
                    Text(
                        text = "Año de Salida: ",
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = oneFilmDetailed.release_date,
                    )
                }
            }
            Column(modifier = Modifier.weight(1f)) {
                Button(onClick = {
                    listScreenViewModel.modificarShow(true)
                }) {
                    Text(text = "Mostrar más")
                }
            }
        }
        MyDialog(
            show = listScreenViewModel.pillarShow(),
            onDismiss = { listScreenViewModel.modificarShow(false) },
            filmItem = oneFilmDetailed
        )
    }
}
