package com.jesinkey.aoc

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
        val outputMoons = inputMoons.map { it.copy() }
        var timeStep = 0L
        do  {
            applyGravity(outputMoons)
            applyVelocity(outputMoons)
            timeStep ++
            if (timeStep % 1000000L == 0L) {
                println(timeStep)
            }
        } while (inputMoons != outputMoons)
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
}