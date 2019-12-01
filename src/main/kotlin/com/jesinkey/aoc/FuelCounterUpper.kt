package com.jesinkey.aoc

class FuelCounterUpper {
    fun requiredFuel(module: Module): Int {
        return module.mass / 3 - 2
    }

}
