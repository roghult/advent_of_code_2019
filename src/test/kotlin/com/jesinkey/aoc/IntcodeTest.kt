package com.jesinkey.aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class IntcodeTest {

    private val startSequenceToExpected = listOf(
        listOf(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50) to listOf(3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50),
        listOf(1,0,0,0,99) to listOf(2,0,0,0,99),
        listOf(2,3,0,3,99) to listOf(2,3,0,6,99),
        listOf(2,4,4,5,99,0) to listOf(2,4,4,5,99,9801),
        listOf(1002,4,3,4,33) to listOf(1002,4,3,4,99),
        listOf(1101,100,-1,4,0) to listOf(1101,100,-1,4,99),
        listOf(1,1,1,4,99,5,6,0,99) to listOf(30,1,1,4,2,5,6,0,99)
    )

    @Test
    fun `sequence`() {
        for ((start, expected) in startSequenceToExpected) {
            val subject = Intcode(start.toMutableList())
            val result = subject.run()
            assertEquals(expected, result)
        }
    }
}
