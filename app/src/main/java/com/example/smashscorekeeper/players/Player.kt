package com.example.smashscorekeeper.players

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.lang.NumberFormatException

class Player(
    val name: String,
    // Ints for entire game.
    var killCount: Int = 0,
    var damageGiven: Int = 0,
    var selfDeaths: Int = 0,
    private var place: Int = 0,
    var finalScore: Int = 0,
    var wins: Int = 0,

    // Temporary Ints for round.
    private var roundKillCount: Int = 0,
    private var roundDamageGiven: Int =0,
    private var roundSelfDeaths: Int = 0,
    private var roundPlace: Int = 0,

    // TextField values.
    private val _textKillCount: MutableLiveData<String> = MutableLiveData(""),
    val textKillCount: LiveData<String> = _textKillCount,

    private val _textDamageGiven: MutableLiveData<String> = MutableLiveData(""),
    val textDamageGiven: LiveData<String> = _textDamageGiven,

    private val _textSelfDeaths: MutableLiveData<String> = MutableLiveData(""),
    val textSelfDeaths: LiveData<String> = _textSelfDeaths,

    private val _textPlace: MutableLiveData<String> = MutableLiveData(""),
    val textPlace: LiveData<String> = _textPlace
) {
    // Method that totals statistics based off round stats.
    fun finalizeStats() {
        killCount = killCount.plus(roundKillCount)
        roundKillCount = 0
        _textKillCount.value = ""

        damageGiven = damageGiven.plus(roundDamageGiven)
        roundDamageGiven = 0
        _textDamageGiven.value = ""

        selfDeaths = selfDeaths.plus(roundSelfDeaths)
        roundSelfDeaths = 0
        _textSelfDeaths.value = ""

        place = place.minus(roundPlace - 1)
        if (roundPlace == 1) {
            wins = wins.plus(1)
        }
        roundPlace = 1
        _textPlace.value = ""

        finalScore =
            (killCount.minus(selfDeaths)).plus(place).plus(damageGiven.div(100))
    }

    fun updateKillCountValue(stat: String) {
        try {
            roundKillCount = stat.toInt()
        } catch(e: NumberFormatException) {
            return
        }

        _textKillCount.value = stat
    }

    fun updateDamageGivenValue(stat: String) {
        try {
            roundDamageGiven = stat.toInt()
        } catch (e: NumberFormatException) {
            return
        }

        _textDamageGiven.value = stat
    }

    fun updateSelfDeaths(stat: String) {
        try {
            roundSelfDeaths = stat.toInt()
        } catch (e: NumberFormatException) {
            return
        }

        _textSelfDeaths.value = stat
    }

    fun updatePlace(stat: String) {
        try {
            roundPlace = stat.toInt()
        } catch (e: NumberFormatException) {
            return
        }

        _textPlace.value = stat
    }
}
