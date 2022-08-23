package com.example.smashscorekeeper.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smashscorekeeper.ui.SelectPlayersScreen
import com.example.smashscorekeeper.ui.StatTrackingScreen
import com.example.smashscorekeeper.viewmodel.PlayerViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    playerViewModel: PlayerViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SelectPlayers.route
    ) {
        composable(
            route = Screen.SelectPlayers.route
        ) {
            SelectPlayersScreen(navController, playerViewModel)
        }

        composable(
            route = Screen.StatTracking.route
        ) {
            StatTrackingScreen(playerViewModel)
        }

    }
}
