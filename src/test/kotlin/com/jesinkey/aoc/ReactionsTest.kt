package com.jesinkey.aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInputLines

class ReactionsTest {
    @Test
    fun `test create functions`() {
        /*
        10 ORE => 10 A
        1 ORE => 1 B
        7 A, 1 B => 1 C
        7 A, 1 C => 1 D
        7 A, 1 D => 1 E
        7 A, 1 E => 1 FUEL
         */
        val input = readInputLines("10 ORE => 10 A\n1 ORE => 1 B\n7 A, 1 B => 1 C\n7 A, 1 C => 1 D\n7 A, 1 D => 1 E\n7 A, 1 E => 1 FUEL", "src/test/resources/")
        val subject = Reactions()
//        val expected = listOf(
//            Function(listOf(Chemical(10, "ORE")), Chemical(10, "A")),
//            Function(listOf(Chemical(1, "ORE")), Chemical(1, "B")),
//            Function(listOf(Chemical(7, "A"), Chemical(1, "B")), Chemical(1, "C")),
//            Function(listOf(Chemical(7, "A"), Chemical(1, "C")), Chemical(1, "D")),
//            Function(listOf(Chemical(7, "A"), Chemical(1, "D")), Chemical(1, "E")),
//            Function(listOf(Chemical(7, "A"), Chemical(1, "E")), Chemical(1, "FUEL"))
//        )
//        val result = subject.createFunctions(input)
//        assertEquals(expected, result)
    }

    @Test
    fun `test solve`() {
        val input = readInputLines("day_14_test_1.txt", "src/test/resources/")
        val subject = Reactions()
        val expected = 31
        val result = subject.minimumOreForFuel(input)
        assertEquals(expected, result)
    }

    @Test
    fun `test solve 2`() {
        val input = readInputLines("day_14_test_2.txt", "src/test/resources/")
        val subject = Reactions()
        val expected = 165
        val result = subject.minimumOreForFuel(input)
        assertEquals(expected, result)
    }
}