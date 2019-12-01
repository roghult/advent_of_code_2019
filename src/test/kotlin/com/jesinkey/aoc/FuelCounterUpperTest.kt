package com.jesinkey.aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FuelCounterUpperTest {

    @Test
    fun `test mass of 12 requires 2 fuel`() {
        val subject = FuelCounterUpper()

        val result = subject.requiredFuel(Module(12))
        val expectedResult = 2

        assertEquals(expectedResult, result)
    }

    @Test
    fun `test mass of 14 requires 2 fuel`() {
        val subject = FuelCounterUpper()

        val result = subject.requiredFuel(Module(14))
        val expectedResult = 2

        assertEquals(expectedResult, result)
    }

    @Test
    fun `test mass of 1969 requires 654 fuel`() {
        val subject = FuelCounterUpper()

        val result = subject.requiredFuel(Module(1969))
        val expectedResult = 654

        assertEquals(expectedResult, result)
    }

    @Test
    fun `test mass of 100756 requires 33583 fuel`() {
        val subject = FuelCounterUpper()

        val result = subject.requiredFuel(Module(100756))
        val expectedResult = 33583

        assertEquals(expectedResult, result)
    }
}
