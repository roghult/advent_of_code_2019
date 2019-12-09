package com.jesinkey.aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class IntcodeTest {

    @Test
    fun `test add`() {
        val sequenceToExpected = listOf(
            listOf(1, 2, 7, 0, 99) to listOf(1, 7, 7, 0, 99),
            listOf(101, 2, 7, 0, 99) to listOf(2, 2, 7, 0, 99),
            listOf(1001, 2, 7, 0, 99) to listOf(7 + 7, 2, 7, 0, 99),
            listOf(1101, 2, 7, 0, 99) to listOf(9, 2, 7, 0, 99),
            listOf(109, 1, 201, 3, 7, 0, 99) to listOf(7 + 0, 1, 201, 3, 7, 0, 99),
            listOf(109, -1, 2201, 3, 7, 0, 99) to listOf(2201 + 99, -1, 2201, 3, 7, 0, 99)
        )
        for ((sequence, expected) in sequenceToExpected) {
            println(sequence)
            val sequenceMap = sequenceListToMap(sequence.map { it.toLong() })
            val intcode = Intcode(sequenceMap)
            intcode.run()
            val expectedMap = sequenceListToMap(expected.map { it.toLong() })
            assertEquals(expectedMap, intcode.sequence)
        }
    }

    @Test
    fun `test multiply`() {
        val sequenceToExpected = listOf(
            listOf(2, 2, 7, 0, 99) to listOf(7 * 0, 2, 7, 0, 99, 0, 0, 0),
            listOf(102, 2, 7, 0, 99) to listOf(2 * 0, 2, 7, 0, 99, 0, 0, 0),
            listOf(1002, 2, 7, 0, 99) to listOf(7 * 7, 2, 7, 0, 99),
            listOf(1102, 2, 7, 0, 99) to listOf(2 * 7, 2, 7, 0, 99),
            listOf(109, 1, 202, 3, 7, 0, 99) to listOf(7 * 0, 1, 202, 3, 7, 0, 99, 0),
            listOf(109, -1, 2202, 3, 7, 0, 99) to listOf(2202 * 99, -1, 2202, 3, 7, 0, 99)
        )
        for ((sequence, expected) in sequenceToExpected) {
            val sequenceMap = sequenceListToMap(sequence.map { it.toLong() })
            val intcode = Intcode(sequenceMap)
            intcode.run()
            val expectedMap = sequenceListToMap(expected.map { it.toLong() })
            assertEquals(expectedMap, intcode.sequence)
        }
    }

/*
    @Test
    fun `test input`() {
        val input = 11
        val foo = (0..203).map { 0 }.toMutableList()
        foo[0] = 203
        foo[1] = 0
        foo[2] = 99
        foo[203] = input
        val sequenceToExpected = listOf(
//            listOf(3, 0, 99) to listOf(3, 0, 99, 11),
//            listOf(103, 0, 99) to listOf(11, 0, 99),
//            listOf(203, 0, 99) to foo
            listOf(3, 0, 99) to listOf(11, 0, 99),
            listOf(103, 0, 99) to listOf(11, 0, 99),
            listOf(203, 0, 99) to listOf(11, 0, 99)
        )
        for ((sequence, expected) in sequenceToExpected) {
            val intcode = Intcode(sequence.map { it.toLong() }.toMutableList())
            intcode.run(mutableListOf(input))
            assertEquals(expected.map { it.toLong() }, intcode.sequence)
        }
    }

    @Test
    fun `test output`() {
        val sequenceToExpected = listOf(
            listOf(4, 0, 99) to (listOf(4, 0, 99) to 4),
            listOf(104, 0, 99) to (listOf(104, 0, 99) to 0),
            listOf(204, 0, 99) to (listOf(204, 0, 99) to 204)
        )
        for ((sequence, expected) in sequenceToExpected) {
            val intcode = Intcode(sequence.map { it.toLong() }.toMutableList())
            val output = intcode.run()
            assertEquals(expected.first.map { it.toLong() }, intcode.sequence)
            assertEquals(expected.second.toLong(), output)
        }
    }

 */

}
