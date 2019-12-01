package com.jesinkey.aoc

class FuelCounterUpper {

    fun requiredFuel(modules: List<Module>) : Int{
        return modules.sumBy {
            requiredFuel(it.mass)
        }
    }

    private fun requiredFuel(mass: Int): Int {
        val extraFuelNeeded = mass / 3 - 2
        return if (extraFuelNeeded > 0) {
            extraFuelNeeded + requiredFuel(extraFuelNeeded)
        } else {
            0
        }
    }

}
