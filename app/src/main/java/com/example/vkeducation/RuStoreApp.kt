package com.example.vkeducation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vkeducation.app_details.AppDetailsScreen
import com.example.vkeducation.app_list.AppListScreen

enum class RuStoreScreen{
    Apps,
    AppDetails
}

@Composable
fun RuStoreApp() {
    val navController: NavHostController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = RuStoreScreen.Apps.name,
    ){
        composable(route = RuStoreScreen.Apps.name) {
            AppListScreen(
                onAppCardClicked = {
                    navController.navigate(route = RuStoreScreen.AppDetails.name)
                }
            )
        }
        composable(route = RuStoreScreen.AppDetails.name) {
            AppDetailsScreen(
                onBackClicked = {navController.navigate(route = RuStoreScreen.Apps.name)}
            )
        }
    }
}