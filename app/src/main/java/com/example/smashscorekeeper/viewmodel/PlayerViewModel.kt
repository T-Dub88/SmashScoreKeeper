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

}
