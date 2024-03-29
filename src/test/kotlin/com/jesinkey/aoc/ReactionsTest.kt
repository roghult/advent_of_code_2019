package com.jesinkey.aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import readInputLines

class ReactionsTest {

    @Test
    fun `test solve 1`() {
        val input = readInputLines("day_14_test_1.txt", "src/test/resources/")
        val subject = Reactions()
        val expected = 31L
        val result = subject.oreNeededForFuel(1, input)
        assertEquals(expected, result)
    }

    @Test
    fun `test solve 2`() {
        val input = readInputLines("day_14_test_2.txt", "src/test/resources/")
        val subject = Reactions()
        val expected = 165L
        val result = subject.oreNeededForFuel(1, input)
        assertEquals(expected, result)
    }

    @Test
    fun `test solve 3`() {
        val input = readInputLines("day_14_test_3.txt", "src/test/resources/")
        val subject = Reactions()
        val expected = 13312L
        val result = subject.oreNeededForFuel(1, input)
        assertEquals(expected, result)
    }

    @Test
    fun `test solve 4`() {
        val input = readInputLines("day_14_test_4.txt", "src/test/resources/")
        val subject = Reactions()
        val expected = 180697L
        val result = subject.oreNeededForFuel(1, input)
        assertEquals(expected, result)
    }

    @Test
    fun `test solve 5`() {
        val input = readInputLines("day_14_test_5.txt", "src/test/resources/")
        val subject = Reactions()
        val expected = 2210736L
        val result = subject.oreNeededForFuel(1, input)
        assertEquals(expected, result)
    }

//    @Test
//    fun `test max fuel 3`() {
//        val input = readInputLines("day_14_test_3.txt", "src/test/resources/")
//        val subject = Reactions()
//        val expected = 82892753
//        val result = subject.fuelFromOre(input)
//        assertEquals(expected, result)
//    }
}