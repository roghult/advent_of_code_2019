package com.jesinkey.aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DistanceOnMapTest {


    @Test
    fun `test distance to closest crossing wires`() {
        val pathToExpectedDistance = listOf(
            listOf("R8,U5,L5,D3", "U7,R6,D4,L4") to 6,
            listOf("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83") to 159,
            listOf("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7") to 135
        )
        val subject = WireMap()
        for ((paths, expectedDistance) in pathToExpectedDistance) {
            val result = subject.distanceToClosestCrossingWires(paths)
            assertEquals(expectedDistance, result)
        }
    }

    @Test
    fun `test shortest amount of steps for wires to intersection`() {
        val pathToExpectedDistance = listOf(
            listOf("R8,U5,L5,D3", "U7,R6,D4,L4") to 30,
            listOf("R75,D30,R83,U83,L12,D49,R71,U7,L72", "U62,R66,U55,R34,D71,R55,D58,R83") to 610,
            listOf("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51", "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7") to 410
        )
        val subject = WireMap()
        for ((paths, expectedDistance) in pathToExpectedDistance) {
            val result = subject.minStepsToCrossingWires(paths)
            assertEquals(expectedDistance, result)
        }
    }
}
