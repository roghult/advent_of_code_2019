import com.jesinkey.aoc.*
import java.io.File

fun main() {
//    val fuelCounterUpper = FuelCounterUpper()

//    val requiredFuel = fuelCounterUpper.requiredFuel(
//        MODULES.map { Module(it) }
//    )
    // 4995942
//    println(requiredFuel)

//    day2Task1()
//    day2Task2()
//    day3Task1()
//    day3Task2()
//    day4Task1()
//    day4Task2()
//    day5Task1()
//    day5Task2()
//    day6Task1()
//    day6Task2()
//    day7Task1()
    day7Task2()
}

fun day7Task2() {
    println()
    println("Day 7, task 2:")

    val inputFileName = "input_day_7.txt"
    val dir = "src/main/resources/"
    val fullPath = dir + inputFileName
    val input = File(fullPath)
    val inputList = input.readLines().flatMap { it.split(",") }.map { it.toInt() }
    val amplifier = Amplifier(inputList)
    val (phaseSettings, thrusterSignal) = amplifier.run(5..9)
    println("With $phaseSettings found thruster signal $thrusterSignal")
}

fun day7Task1() {
    println()
    println("Day 7, task 1:")

    val inputFileName = "input_day_7.txt"
    val dir = "src/main/resources/"
    val fullPath = dir + inputFileName
    val input = File(fullPath)
    val inputList = input.readLines().flatMap { it.split(",") }.map { it.toInt() }
    val amplifier = Amplifier(inputList)
    val (phaseSettings, thrusterSignal) = amplifier.run(0..4)
    println("With $phaseSettings found thruster signal $thrusterSignal")
}

fun day6Task2() {
    println()
    println("Day 6, task 2:")
    val inputFileName = "input_day_6_task_1.txt"
    val dir = "src/main/resources/"
    val fullPath = dir + inputFileName
    val input = File(fullPath)
    val spaceObjects = SpaceObjectFactory().fromInputText(input.readLines())
    val transfers = minimumTransfersRequired(
        spaceObjects = spaceObjects,
        fromName = "YOU",
        toName = "SAN"
    )
    print("Minimum transfers: $transfers")
}

fun day6Task1() {
    println()
    println("Day 6, task 1:")
    val inputFileName = "input_day_6_task_1.txt"
    val dir = "src/main/resources/"
    val fullPath = dir + inputFileName
    val input = File(fullPath)
    val spaceObjects = SpaceObjectFactory().fromInputText(input.readLines())
    val totalOrbits = totalNumberOfOrbits(spaceObjects)
    print("Total orbits: $totalOrbits")
}

fun day5Task2() {
    println()
    println("Day 5, task 2:")
    val input = INTCODE_INPUT_DAY_5
    val intcode = Intcode(input.toMutableList())
    intcode.run()
}

fun day5Task1() {
    println()
    println("Day 5, task 1:")
    val input = INTCODE_INPUT_DAY_5
    val intcode = Intcode(input.toMutableList())
    intcode.run()
}

fun day4Task2() {
    println()
    println("Day 4, task 2:")
    val from = 246540
    val to = 787419
    val passwordVerification = PasswordVerification()
    val inputToResult = (from..to).associateWith {
        passwordVerification.verify(it.toString())
    }
    val numTrue = inputToResult.values.count { it }

    println("Answer = $numTrue")
}

fun day4Task1() {
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
    val intcode = Intcode(input)
    val result = intcode.run()
    println()
    println("Day 2, task 1:")
    println(result)
}
