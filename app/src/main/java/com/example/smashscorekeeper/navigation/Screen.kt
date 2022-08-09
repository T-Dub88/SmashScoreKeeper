package com.example.smashscorekeeper.navigation

sealed class Screen(val route: String) {
    object SelectPlayers: Screen(route = "select_players")
    object StatTracking: Screen(route = "stat_tracking")
}
