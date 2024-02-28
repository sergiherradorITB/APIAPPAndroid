package com.example.apilist_sergiherrador.View

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import com.example.apilist_sergiherrador.ViewModel.APIViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar(myViewModel:APIViewModel){
    val searchText by myViewModel.searchText.observeAsState("")
    SearchBar(
        query = searchText,
        onQueryChange = {myViewModel.onSearchTextChange(it)},
        onSearch = { myViewModel.onSearchTextChange(it)},
        active = true,
        leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")},
        placeholder = { Text("What are you looking for?")},
        onActiveChange = {}, modifier = Modifier.fillMaxWidth().fillMaxHeight(0.11f).clip(CircleShape)){
    }
}

/* Modo mileurista
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun busqueda(
    searchText: String,
    apiViewModel: APIViewModel
) {
    TextField(
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.Gray
            )
        },
        value = searchText,
        onValueChange = { apiViewModel.onSearchTextChange(it.lowercase()) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 5.dp),
        label = {
            Text(text = "Search")
        }
    )
}

 */