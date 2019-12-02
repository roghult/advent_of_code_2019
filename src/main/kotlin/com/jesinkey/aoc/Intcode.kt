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
