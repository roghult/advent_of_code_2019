package com.jesinkey.aoc

import kotlin.math.pow
import kotlin.math.sqrt

class Asteroid(val x: Int, val y: Int) {

    lateinit var spottedAsteroids: List<Pair<Asteroid, K>>

    fun distance(asteroid: Asteroid): Double {
        val xpow2 = (asteroid.x - x.toDouble()).pow(2)
        val ypow2 = (asteroid.y - y.toDouble()).pow(2)
        return sqrt(xpow2 + ypow2)
    }

    override fun toString(): String {
        return "($x, $y)"
    }

    override fun equals(other: Any?): Boolean {
        return x == (other as Asteroid).x && y == (other).y
    }

}