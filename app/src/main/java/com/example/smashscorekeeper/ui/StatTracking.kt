package com.example.smashscorekeeper.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smashscorekeeper.players.Player
import com.example.smashscorekeeper.ui.theme.Red900
import com.example.smashscorekeeper.viewmodel.PlayerViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun StatTrackingScreen(playerViewModel: PlayerViewModel) {
    val playerList by playerViewModel.playerList.observeAsState(initial = listOf())
    val roundNumber by playerViewModel.roundNumber.observeAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    StatTrackingContent(
        playerList = playerList,
        roundNumber = roundNumber,
        endRound = {
            playerViewModel.endRound()
            keyboardController?.hide()
            focusManager.clearFocus()
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatTrackingContent(
    playerList: List<Player>,
    roundNumber: Int?,
    endRound: () -> Unit
) {
    Scaffold(
        containerColor = Color.Transparent,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { endRound() },
                containerColor = Red900
            ) {
                Text(text = "Round $roundNumber", modifier = Modifier.padding(8.dp))
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) {
        LazyColumn {
            items(playerList) { player ->
                // Observers for TextField values.
                val textKillCount: String by player.textKillCount.observeAsState(initial = "")
                val textDamageGive: String by player.textDamageGiven.observeAsState(initial = "")
                val textSelfDeaths: String by player.textSelfDeaths.observeAsState(initial = "")
                val textPlace: String by player.textPlace.observeAsState(initial = "")

                Card (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    ),
                    border = CardDefaults.outlinedCardBorder(),

                    ) {

                    Text(
                        text = player.name,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )

                    Row {
                        Text(
                            text = "Total Score: ${player.finalScore}",
                            fontSize = 25.sp,
                            modifier = Modifier
                                .fillMaxWidth(.5f)
                                .padding(start = 8.dp)
                        )

                        Text(
                            text = "Total Stats:",
                            fontSize = 25.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }

                    Row {
                        OutlinedTextField (
                            value = textKillCount,
                            onValueChange = { player.updateKillCountValue(it) },
                            label = { Text("Kills") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .fillMaxWidth(.5f)
                                .padding(start = 8.dp)
                        )

                        Text(
                            text = "${player.killCount}",
                            fontSize = 25.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }

                    Row {
                        OutlinedTextField (
                            value = textDamageGive,
                            onValueChange = { player.updateDamageGivenValue(it) },
                            label = { Text("Damage Given") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .fillMaxWidth(.5f)
                                .padding(start = 8.dp)
                        )

                        Text(
                            text = "${player.damageGiven}",
                            fontSize = 25.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }

                    Row {
                        OutlinedTextField (
                            value = textSelfDeaths,
                            onValueChange = { player.updateSelfDeaths(it) },
                            label = { Text("SDs") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .fillMaxWidth(.5f)
                                .padding(start = 8.dp)
                        )

                        Text(
                            text = "${player.selfDeaths}",
                            fontSize = 25.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }

                    Row {
                        OutlinedTextField (
                            value = textPlace,
                            onValueChange = { player.updatePlace(it) },
                            label = { Text("Place") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .fillMaxWidth(.5f)
                                .padding(bottom = 8.dp, start = 8.dp)
                        )

                        Text(
                            text = "Wins: ${player.wins}",
                            fontSize = 25.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
