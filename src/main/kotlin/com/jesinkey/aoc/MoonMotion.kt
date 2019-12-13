package com.jesinkey.aoc

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class MoonMotion {

    fun simulate(inputMoons: List<Moon>, timeSteps: Int): List<Moon> {
        val outputMoons = inputMoons.map { it.copy() }
        for (timeStep in 0 until timeSteps) {
            applyGravity(outputMoons)
            applyVelocity(outputMoons)
        }
        return outputMoons
    }

    fun stepsUntilSamePosition(inputMoons: List<Moon>): Long {
        val startX = inputMoons.flatMap { listOf(it.x, it.xVelocity) }
        val startY = inputMoons.flatMap { listOf(it.y, it.yVelocity) }
        val startZ = inputMoons.flatMap { listOf(it.z, it.zVelocity) }
        val outputMoons = inputMoons.map { it.copy() }
        var timeStep = 0L
        var xRerunsEvery = 0L
        var yRerunsEvery = 0L
        var zRerunsEvery = 0L
        do  {
            applyGravity(outputMoons)
            applyVelocity(outputMoons)
            timeStep ++
            if (timeStep % 1000000L == 0L) {
                println(timeStep)
            }
            val xNow = outputMoons.flatMap { listOf(it.x, it.xVelocity) }
            if (xNow == startX && xRerunsEvery == 0L) {
                xRerunsEvery = timeStep
            }
            if (xNow == startX) {
                val foo = 0L == timeStep % xRerunsEvery
                println()
            }
            val yNow = outputMoons.flatMap { listOf(it.y, it.yVelocity) }
            if (yNow == startY && yRerunsEvery == 0L) {
                yRerunsEvery = timeStep
            }
            if (yNow == startY) {
                val foo = 0L == timeStep % yRerunsEvery
                println()
            }
            val zNow = outputMoons.flatMap { listOf(it.z, it.zVelocity) }
            if (zNow == startZ && zRerunsEvery == 0L) {
                zRerunsEvery = timeStep
            }

            if (zNow == startZ) {
                val foo = 0L == timeStep % zRerunsEvery
                println()
            }
        } while (listOf(xRerunsEvery, yRerunsEvery, zRerunsEvery).any { it == 0L })

        /*
        xRerunsEvery = 2028L
        yRerunsEvery = 5898L
        zRerunsEvery = 4702L
        lcm = 543673227860472
         */
        return timeStep
    }

    private fun applyVelocity(moons: List<Moon>) {
        for (moon in moons) {
            moon.updateCoordinates()
        }
    }

    private fun applyGravity(moons: List<Moon>) {
        for (moon in moons) {
            moon.applyGravityFrom(moons.filterNot { it == moon })
        }
    }

    fun lcm2(number1: Long, number2: Long): Long {
        if (number1 == 0L || number2 == 0L) {
            return 0
        }
        val absNumber1 = abs(number1)
        val absNumber2 = abs(number2)
        val absHigherNumber = max(absNumber1, absNumber2)
        val absLowerNumber = min(absNumber1, absNumber2)
        var lcm = absHigherNumber
        while (lcm % absLowerNumber != 0L) {
            lcm += absHigherNumber
        }
        return lcm
    }


    private fun lcm(a: Long, b: Long): Long {
        return a * (b / gcd(listOf(a, b)))
    }

    private fun gcd(input: List<Long>): Long {
        var result = input[0]
        for (i in 1 until input.size) result = lcm(result, input[i])
        return result
    }

    fun gcd3(a: Long, b: Long): Long = if (b == 0L) a else gcd3(b, a % b)
    fun lcm3(a: Long, b: Long): Long = a / gcd3(a, b) * b
}