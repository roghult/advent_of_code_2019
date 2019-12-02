import com.jesinkey.aoc.*

fun main() {
    val fuelCounterUpper = FuelCounterUpper()

    val requiredFuel = fuelCounterUpper.requiredFuel(
        MODULES.map { Module(it) }
    )
    // 4995942
    println(requiredFuel)

    day_2_task_1()
}

private fun day_2_task_1() {
    /*
    Once you have a working computer, the first step is to restore the gravity assist program (your puzzle input)
    to the "1202 program alarm" state it had just before the last computer caught fire. To do this, before running
    the program, replace position 1 with the value 12 and replace position 2 with the value 2.
     */
    val input = INTCODE_INPUT.toMutableList()
    input[1] = 12
    input[2] = 2
    val intcode = Intcode()
    val result = intcode.run(input)
    println()
    println("Day 2, task 1:")
    println(result[0])
}
