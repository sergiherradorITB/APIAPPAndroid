import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.tartita.apilist.Model.AllFilms
import com.tartita.apilist.Model.DetailFilmItem
import com.tartita.apilist.Routes
import com.tartita.apilist.ViewModel.ListDetailScreenViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GhibliItem(
    ghibli: AllFilms,
    navController: NavController,
    listScreenViewModel: ListDetailScreenViewModel
) {
    Card(border = BorderStroke(2.dp, Colores.LilaBorde.color), modifier = Modifier.fillMaxWidth()) {
        Column() {
            Row {
                GlideImage(
                    model = ghibli.image,
                    contentDescription = "Character Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(100.dp)
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(text = ghibli.title)
                    Text(text = ghibli.original_title)

                    Button(modifier = Modifier.padding(top = 2.dp),
                        onClick = {
                            //listScreenViewModel.modificarGhibli(ghibli)
                            listScreenViewModel.modificarGhibliId(ghibli.id)
                            navController.navigate(Routes.DetailScreen.route)
                        }) {
                        Text(text = "Ver detalles")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GhibliItem(
    ghibli: DetailFilmItem,
    navController: NavController,
    listScreenViewModel: ListDetailScreenViewModel
) {
    Card(border = BorderStroke(2.dp, Colores.LilaBorde.color), modifier = Modifier.fillMaxWidth()) {
        Column {
            Row {
                GlideImage(
                    model = ghibli.image,
                    contentDescription = "Character Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(100.dp)
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(text = ghibli.title)
                    Text(text = ghibli.original_title)

                    Button(modifier = Modifier.padding(top = 2.dp),
                        onClick = {
                            //listScreenViewModel.modificarGhibliFavorite(ghibli)
                            listScreenViewModel.modificarGhibliId(ghibli.id)
                            navController.navigate(Routes.DetailScreen.route)
                        }) {
                        Text(text = "Ver detalles")
                    }
                }
            }
        }
    }
}