package com.jesinkey.aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LayerTest {

    @Test
    fun `test layer with fewest 0s`() {
        /*
        Layer 1: 123
                 456

        Layer 2: 789
                 012
         */
        val input = "123456789012"
        val width = 3
        val height = 2
        val image = Image(input, width, height)
        val expected = 1
        val result = image.layerWithLeast(0)
        assertEquals(expected, result)
    }

    @Test
    fun `test layer with fewest 6s`() {
        /*
        Layer 1: 123
                 456

        Layer 2: 789
                 012
         */
        val input = "123456789012"
        val width = 3
        val height = 2
        val image = Image(input, width, height)
        val expected = 2
        val result = image.layerWithLeast(6)
        assertEquals(expected, result)
    }

    @Test
    fun `test occurrences in layer`() {
        /*
        Layer 1: 123
                 453

        Layer 2: 783
                 012
         */
        val input = "123453783012"
        val width = 3
        val height = 2
        val image = Image(input, width, height)
        assertEquals(2, image.occurrencesInLayer(1, 3))
        assertEquals(1, image.occurrencesInLayer(2, 3))
    }
}
