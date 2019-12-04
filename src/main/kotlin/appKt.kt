import com.jesinkey.aoc.*

fun main() {
    val fuelCounterUpper = FuelCounterUpper()

    val requiredFuel = fuelCounterUpper.requiredFuel(
        MODULES.map { Module(it) }
    )
    // 4995942
    println(requiredFuel)

    day2Task1()
    day2Task2()
    day3Task1()
    day3Task2()
    day3Task3()
}

fun day3Task3() {
    println()
    println("Day 4, task 1:")
    val from = 246540
    val to = 787419
    val passwordVerification = PasswordVerification()
    val inputToResult = (from..to).associateWith {
        passwordVerification.verify(it.toString())
    }
    val numTrue = inputToResult.values.count { it }

    println("Answer = $numTrue")
}

fun day3Task2() {
    println()
    println("Day 3, task 2:")
    val input = WIRE_MAP_INPUT
    val wireMap = WireMap()
    val shortestSteps = wireMap.minStepsToCrossingWires(input)
    println("Answer = $shortestSteps")
}

private fun day3Task1() {
    println()
    println("Day 3, task 1:")
    val input = WIRE_MAP_INPUT
    val wireMap = WireMap()
    val shortestDistance = wireMap.distanceToClosestCrossingWires(input)
    println("Answer = $shortestDistance")
}

private fun day2Task2() {
    println()
    println("Day 2, task 2:")
    val desiredOutput = 19690720
    val nounAndVerb = nounAndVerbFinder(
        input = INTCODE_INPUT,
        desiredOutput = desiredOutput,
        nouns = (0..99).toList(),
        verbs = (0..99).toList()
    )!!
    println("Found $desiredOutput with noun = ${nounAndVerb.noun} and verb = ${nounAndVerb.verb}")

    // What is 100 * noun + verb?
    println("Answer = ${100 * nounAndVerb.noun + nounAndVerb.verb}")
}

private fun day2Task1() {
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
