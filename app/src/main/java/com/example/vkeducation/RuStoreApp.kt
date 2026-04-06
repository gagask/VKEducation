package com.example.vkeducation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.vkeducation.presentation.appdetails.AppDetailsScreen
import com.example.vkeducation.presentation.applist.AppListScreen
import com.example.vkeducation.presentation.navigation.Screen


@Composable
fun RuStoreApp() {
    val navController: NavHostController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = Screen.List,
    ){
        composable<Screen.List> {
            AppListScreen(
                onAppCardClicked = { id ->
                    navController.navigate(route = Screen.Details(id))
                }
            )
        }
        composable<Screen.Details> {
            AppDetailsScreen(
                onBackClicked = {navController.navigate(route = Screen.List)}
            )
        }
    }
}