package com.example.apilist_sergiherrador.View

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.apilist_sergiherrador.ViewModel.APIViewModel

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