package com.jesinkey.aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PermutationsTest {
    @Test
    fun `test 1,2,3`() {
        val input = listOf(1L, 2L, 3L)
        val expected = listOf(
            listOf(1L, 2L, 3L),
            listOf(1L, 3L, 2L),
            listOf(2L, 3L, 1L),
            listOf(2L, 1L, 3L),
            listOf(3L, 1L, 2L),
            listOf(3L, 2L, 1L)
        )
        val result = heapsPermutations(input)
        assertEquals(expected.size, result.size)
        assertEquals(expected.toSet(), result.toSet())
    }
}