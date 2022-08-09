package com.example.smashscorekeeper.ui

import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smashscorekeeper.navigation.Screen
import com.example.smashscorekeeper.players.Player
import com.example.smashscorekeeper.viewmodel.PlayerViewModel


@Composable
fun SelectPlayersScreen(navController: NavController, playerViewModel: PlayerViewModel = viewModel()) {
    val playerName: String by playerViewModel.playerNameText.observeAsState(initial = "")
    val playerList by playerViewModel.playerList.observeAsState(initial = emptyList())
    SelectPlayersContent(
        viewModel = playerViewModel,
        navController = navController,
        playerName = playerName,
        onNameChange = { playerViewModel.onNameChange(it) },
        playerList = playerList
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectPlayersContent(playerList: List<Player>, viewModel: PlayerViewModel, navController: NavController, playerName: String, onNameChange: (String) -> Unit) {
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
                    viewModel.addPlayer(playerName)
                    Log.i("list", viewModel.playerList.value.toString())
                    viewModel.onNameChange("")
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
                    viewModel.removePlayer(playerName)
                    viewModel.onNameChange("")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 16.dp)
            ) {
                Text(text = "Delete Player")
            }
        }

        Button(
            onClick = { navController.navigate(route = Screen.StatTracking.route) },
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

@Composable
@Preview(showBackground = true)
fun SelectPlayersScreenPreview() {
    SelectPlayersScreen(navController = rememberNavController())
}
