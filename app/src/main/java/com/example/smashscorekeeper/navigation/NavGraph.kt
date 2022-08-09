package com.example.smashscorekeeper.navigation

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smashscorekeeper.ui.SelectPlayersScreen
import com.example.smashscorekeeper.ui.StatTrackingScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SelectPlayers.route
    ) {
        composable(
            route = Screen.SelectPlayers.route
        ) {
            SelectPlayersScreen(navController)
        }

        composable(
            route = Screen.StatTracking.route
        ) {
            StatTrackingScreen(navController)
        }

    }
}