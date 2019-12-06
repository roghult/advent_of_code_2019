package com.jesinkey.aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class SpaceObjectTest {

    private val dir = "src/test/resources/"
    private val inputFileName = "input_space_object_test.txt"
    private val fullPath = dir + inputFileName

    @Test
    fun `test factory`() {
        val subject = SpaceObjectFactory()
        val com = SpaceObject("COM")
        val b = SpaceObject("B", com)
        val c = SpaceObject("C", b)
        val d = SpaceObject("D", c)
        val e = SpaceObject("E", d)
        val f = SpaceObject("F", e)
        val g = SpaceObject("G", b)
        val h = SpaceObject("H", g)
        val i = SpaceObject("I", d)
        val j = SpaceObject("J", e)
        val k = SpaceObject("K", j)
        val l = SpaceObject("L", k)
        val expected = listOf(com, b, c, d, e, f, g, h, i, j, k, l)
        val input = File(fullPath)
        val result = subject.fromInputText(input.readLines())
        assertEquals(expected.size, result.size)
        assertEquals(expected.toSet(), result.toSet())
    }

    @Test
    fun `num orbits COM`() {
        val factory = SpaceObjectFactory()
        val input = File(fullPath)
        val spaceObjects = factory.fromInputText(input.readLines())
        val com = spaceObjects.single { it.name == "COM" }
        val expected = 0
        val result = com.numberOfOrbits()
        assertEquals(expected, result)
    }

    @Test
    fun `num orbits D`() {
        val factory = SpaceObjectFactory()
        val input = File(fullPath)
        val spaceObjects = factory.fromInputText(input.readLines())
        val d = spaceObjects.single { it.name == "D" }
        val expected = 3
        val result = d.numberOfOrbits()
        assertEquals(expected, result)
    }

    @Test
    fun `num orbits L`() {
        val factory = SpaceObjectFactory()
        val input = File(fullPath)
        val spaceObjects = factory.fromInputText(input.readLines())
        val l = spaceObjects.single { it.name == "L" }
        val expected = 7
        val result = l.numberOfOrbits()
        assertEquals(expected, result)
    }

    @Test
    fun `total number of orbits`() {
        val factory = SpaceObjectFactory()
        val input = File(fullPath)
        val spaceObjects = factory.fromInputText(input.readLines())
        val expected = 42
        val result = totalNumberOfOrbits(spaceObjects)
        assertEquals(expected, result)
    }
}