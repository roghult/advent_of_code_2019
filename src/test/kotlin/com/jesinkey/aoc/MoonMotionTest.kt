package com.jesinkey.aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MoonMotionTest {

    @Test
    fun `test 1 step`() {
        val moons = listOf(
            Moon(-1, 0, 2),
            Moon(2, -10, -7),
            Moon(4, -8, 8),
            Moon(3, 5, -1)
        )
        val subject = MoonMotion()
        val result = subject.simulate(moons, 1)
        val expected = listOf(
            Moon(2, -1, 1, 3, -1, -1),
            Moon(3, -7, -4, 1, 3, 3),
            Moon(1, -7, 5, -3, 1, -3),
            Moon(2, 2, 0, -1, -3, 1)
        )
        assertEquals(expected, result)
    }

    @Test
    fun `test 10 steps`() {
        val moons = listOf(
            Moon(-1, 0, 2),
            Moon(2, -10, -7),
            Moon(4, -8, 8),
            Moon(3, 5, -1)
        )
        val subject = MoonMotion()
        val result = subject.simulate(moons, 10)
        val expected = listOf(
            Moon(2, 1, -3, -3, -2, 1),
            Moon(1, -8, 0, -1, 1, 3),
            Moon(3, -6, 1, 3, 2, -3),
            Moon(2, 0, 4, 1, -1, -1)
        )
        expected.forEachIndexed { index, moon ->
            val moonResult = result[index]
            assertEquals(moon, moonResult)
        }
    }

    @Test
    fun `energy after 10 steps`() {
        val moons = listOf(
            Moon(-1, 0, 2),
            Moon(2, -10, -7),
            Moon(4, -8, 8),
            Moon(3, 5, -1)
        )
        val subject = MoonMotion()
        val expected = 179
        val moonsAfterSteps = subject.simulate(moons, 10)
        val totalEnergy = moonsAfterSteps.sumBy { it.totalEnergy() }
        assertEquals(expected, totalEnergy)
    }


    @Test
    fun `steps until repeat`() {
        val moons = listOf(
            Moon(-1, 0, 2),
            Moon(2, -10, -7),
            Moon(4, -8, 8),
            Moon(3, 5, -1)
        )
        val subject = MoonMotion()
        val expected = 2772
        val result = subject.stepsUntilSamePosition(moons)
        assertEquals(expected, result)
    }

}