package com.jesinkey.aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FuelCounterUpperTest {

    @Test
    fun `test mass of 12 requires 2 fuel`() {
        val subject = FuelCounterUpper()

        val result = subject.requiredFuel(listOf(Module(12)))
        val expectedResult = 2

        assertEquals(expectedResult, result)
    }

    @Test
    fun `test mass of 14 requires 2 fuel`() {
        val subject = FuelCounterUpper()

        val result = subject.requiredFuel(listOf(Module(14)))
        val expectedResult = 2

        assertEquals(expectedResult, result)
    }

    @Test
    fun `test mass of 1969 requires 966 fuel`() {
        val subject = FuelCounterUpper()

        val result = subject.requiredFuel(listOf(Module(1969)))
        val expectedResult = 966

        assertEquals(expectedResult, result)
    }

    @Test
    fun `test mass of 100756 requires 50346 fuel`() {
        val subject = FuelCounterUpper()

        val result = subject.requiredFuel(listOf(Module(100756)))
        val expectedResult = 50346

        assertEquals(expectedResult, result)
    }
}
