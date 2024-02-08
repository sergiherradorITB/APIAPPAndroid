package com.example.apilist_sergiherrador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apilist_sergiherrador.View.ListScreen
import com.example.apilist_sergiherrador.View.*
import com.example.apilist_sergiherrador.ViewModel.APIViewModel
import com.example.apilist_sergiherrador.ViewModel.ListScreenViewModel
import com.example.apilist_sergiherrador.ui.theme.APIListSergiHerradorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navigationController = rememberNavController()
            val apiViewModel by viewModels<APIViewModel>()
            val listScreenViewModel by viewModels<ListScreenViewModel>()
            NavHost(
                navController = navigationController,
                startDestination = Routes.ListScreen.route
            ){
                composable(Routes.ListScreen.route){
                    ListScreen(navigationController, apiViewModel, listScreenViewModel)
                }
                composable(Routes.DetailScreen.route){
                    DetailScreen(navigationController, listScreenViewModel)
                }
            }
        }
    }
}
