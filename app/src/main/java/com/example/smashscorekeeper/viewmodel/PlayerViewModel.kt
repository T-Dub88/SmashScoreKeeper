package com.example.smashscorekeeper.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smashscorekeeper.players.Player

class PlayerViewModel: ViewModel() {

    // Player name variable for text entry field.
    private val _playerNameText: MutableLiveData<String> = MutableLiveData("")
    val playerNameText: LiveData<String> = _playerNameText

    // List of created player objects.
    private val _playerList: MutableLiveData<List<Player>> = MutableLiveData(listOf())
    val playerList: LiveData<List<Player>> = _playerList

    // Variable to track the round number.
    private val _roundNumber: MutableLiveData<Int> = MutableLiveData(1)
    val roundNumber: LiveData<Int> = _roundNumber

    // Changes the display name in the name entry box.
    fun onNameChange(name: String) {
        _playerNameText.value = name
    }

    // Add player to list of player objects.
    fun addPlayer(name: String) {
        val player = Player(name.trim())
        _playerList.value = _playerList.value?.toMutableList()?.plus(player)
    }

    // Remove player from player objects list.
    fun removePlayer(name: String) {
        val player: Player = _playerList.value?.find { it.name == name } ?: return
        _playerList.value = _playerList.value?.toMutableList()?.minus(player)
    }

    // Calls all calculations to end a round.
    fun endRound() {
        // Increases the round count.
        _roundNumber.value = _roundNumber.value?.plus(1)

        // Calls finalize stats for each player object in list.
        for (player in _playerList.value!!) {
            player.finalizeStats()
        }
    }
}
