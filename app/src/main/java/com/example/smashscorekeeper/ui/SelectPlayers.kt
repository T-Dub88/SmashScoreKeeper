package com.example.smashscorekeeper.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.smashscorekeeper.navigation.Screen
import com.example.smashscorekeeper.players.Player
import com.example.smashscorekeeper.viewmodel.PlayerViewModel

@Composable
fun SelectPlayersScreen(navController: NavController, playerViewModel: PlayerViewModel) {
    val playerName: String by playerViewModel.playerNameText.observeAsState(initial = "")
    val playerList by playerViewModel.playerList.observeAsState(initial = emptyList())
    SelectPlayersContent(
        playerName = playerName,
        onNameChange = { playerViewModel.onNameChange(it) },
        addPlayer = { playerViewModel.addPlayer(it) },
        removePlayer = { playerViewModel.removePlayer(it) },
        startGame = { navController.navigate(route = Screen.StatTracking.route) },
        playerList = playerList
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectPlayersContent(
    playerName: String,
    onNameChange: (String) -> Unit,
    addPlayer: (String) -> Unit,
    removePlayer: (String) -> Unit,
    startGame: () -> Unit,
    playerList: List<Player>
) {
    Column {
        OutlinedTextField(
            value = playerName,
            onValueChange = onNameChange,
            label = { Text("Enter Player Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        )

        Row {
            Button(
                onClick = {
                    addPlayer(playerName)
                    onNameChange("")
                },
                modifier = Modifier
                    .fillMaxWidth(.5f)
                    .padding(start = 16.dp, end = 8.dp)

            ) {
                Text(
                    text = "Add Player"
                )
            }

            Button(
                onClick = {
                    removePlayer(playerName)
                    onNameChange("")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 16.dp)
            ) {
                Text(text = "Delete Player")
            }
        }

        Button(
            onClick = startGame,
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Start Game")
        }

        LazyColumn {
            items(playerList) { player ->
                Text(
                    text = player.name,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontSize = 30.sp
                )
            }
        }
    }
}
