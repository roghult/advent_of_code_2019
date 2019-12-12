package com.jesinkey.aoc

import kotlin.math.absoluteValue

data class Moon(
    var x: Int,
    var y: Int,
    var z: Int,
    var xVelocity: Int = 0,
    var yVelocity: Int = 0,
    var zVelocity: Int = 0
) {
    fun potentialEnergy(): Int {
        return x.absoluteValue + y.absoluteValue + z.absoluteValue
    }

    fun kineticEnergy(): Int {
        return xVelocity.absoluteValue + yVelocity.absoluteValue + zVelocity.absoluteValue
    }

    fun totalEnergy(): Int {
        return potentialEnergy() * kineticEnergy()
    }

    fun applyGravityFrom(moons: List<Moon>) {
        xVelocity += moons.sumBy { velocityChange(x, it.x) }
        yVelocity += moons.sumBy { velocityChange(y, it.y) }
        zVelocity += moons.sumBy { velocityChange(z, it.z) }
    }

    private fun velocityChange(myValue: Int, otherValue: Int): Int {
        val change = when {
            myValue < otherValue -> 1
            myValue == otherValue -> 0
            myValue > otherValue -> -1
            else -> TODO()
        }
        return change
    }

    fun updateCoordinates() {
        x += xVelocity
        y += yVelocity
        z += zVelocity
    }

    override fun equals(other: Any?): Boolean {
        val otherMoon = other as Moon
        return listOf(
            x == other.x,
            y == other.y,
            z == other.z,
            xVelocity == other.xVelocity,
            yVelocity == other.yVelocity,
            zVelocity == other.zVelocity
        ).all { it }
    }
}