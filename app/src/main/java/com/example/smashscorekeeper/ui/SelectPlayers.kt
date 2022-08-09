package com.example.smashscorekeeper.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.smashscorekeeper.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectPlayersScreen(navController: NavController) {

    val playerNameText = rememberSaveable {
        mutableStateOf("")
    }

    Column {

        OutlinedTextField(
            value = playerNameText.value,
            onValueChange = {playerNameText.value = it},
            label = { Text("Enter Player Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        )

        Row {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth(.5f)
                    .padding(start = 16.dp, end = 8.dp)

                ) {
                Text(
                    text = "Add Player"
                )
            }

            Button(
                onClick = { /*TODO*/ },
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

    }
}

@Composable
@Preview(showBackground = true)
fun SelectPlayersScreenPreview() {
    SelectPlayersScreen(navController = rememberNavController())
}
