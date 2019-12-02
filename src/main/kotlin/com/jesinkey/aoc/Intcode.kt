package com.jesinkey.aoc

class Intcode {

    private val OPCODE = mapOf(
        1 to {x: Int, y: Int -> x + y},
        2 to {x: Int, y: Int -> x * y}
    )

    fun run(sequence: List<Int>): List<Int> {
        val newSequence = sequence.toMutableList()
        for (i in 0 until newSequence.size step 4) {
            val opcode = newSequence[i]
            if (opcode == 99) {
                return newSequence
            }
            val posValue1 = newSequence[i + 1]
            val posValue2 = newSequence[i + 2]
            val outputPosition = newSequence[i + 3]
            val value1 = newSequence[posValue1]
            val value2 = newSequence[posValue2]

            newSequence[outputPosition] = OPCODE.getValue(opcode)(value1, value2)
        }
        return newSequence
    }
}

data class NounAndVerb(
    val noun: Int,
    val verb: Int
)

fun nounAndVerbFinder(input: List<Int>, desiredOutput: Int, nouns: List<Int>, verbs: List<Int>): NounAndVerb? {
    val intcode = Intcode()
    for (noun in nouns) {
        for (verb in verbs) {
            val input = INTCODE_INPUT.toMutableList()
            input[1] = noun
            input[2] = verb
            val result = intcode.run(input)
            if (result[0] == desiredOutput) {
                return NounAndVerb(noun, verb)
            }
        }
    }
    return null
}
