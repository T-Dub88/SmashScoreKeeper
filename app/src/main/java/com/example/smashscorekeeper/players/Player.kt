package com.example.smashscorekeeper.players

class Player(
    val name: String,
    var killCount: Int = 0,
    var damageGiven: Int = 0,
    var selfDeaths: Int = 0,
    var place: Int = 0,
    var finalScore: Int = 0,
    var wins: Int = 0,
    var roundKillCount: Int = 0,
    var roundDamageGiven: Int =0,
    var roundSelfDeaths: Int = 0,
    var roundPlace: Int = 0
) {
    // Method that totals statistics based off round stats.
    fun finalizeStats() {
        killCount = killCount.plus(roundKillCount)
        roundKillCount = 0

        damageGiven = damageGiven.plus(roundDamageGiven)
        roundDamageGiven = 0

        selfDeaths = selfDeaths.plus(roundSelfDeaths)
        roundSelfDeaths = 0

        place = place.minus(roundPlace - 1)
        if (roundPlace == 1) {
            wins = wins.plus(1)
        }
        roundPlace = 1

        finalScore =
            (killCount.minus(selfDeaths)).plus(place).plus(damageGiven.div(100))
    }
}