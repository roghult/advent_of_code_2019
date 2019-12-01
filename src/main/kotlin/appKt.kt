import com.jesinkey.aoc.FuelCounterUpper
import com.jesinkey.aoc.MODULES
import com.jesinkey.aoc.Module

fun main() {
    val fuelCounterUpper = FuelCounterUpper()

    val requiredFuel = fuelCounterUpper.requiredFuel(
        MODULES.map { Module(it) }
    )
    // 4995942
    println(requiredFuel)
}
