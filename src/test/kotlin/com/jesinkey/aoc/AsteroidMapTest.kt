package com.jesinkey.aoc

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AsteroidMapTest {
    @Test
    fun `asteroid map`() {
        val input = "#.........\n" +
                "...#......\n" +
                "...#..#...\n" +
                ".####....#\n" +
                "..#.#.#...\n" +
                ".....#....\n" +
                "..###.#.##\n" +
                ".......#..\n" +
                "....#...#.\n" +
                "...#..#..#"
        val subject = AsteroidMap(input)
        val expected = listOf(
            Asteroid(0, 0),
            Asteroid(3, 1),
            Asteroid(3, 2),
            Asteroid(6, 2),
            Asteroid(1, 3),
            Asteroid(2, 3),
            Asteroid(3, 3),
            Asteroid(4, 3),
            Asteroid(9, 3),
            Asteroid(2, 4),
            Asteroid(4, 4),
            Asteroid(6, 4),
            Asteroid(5, 5),
            Asteroid(2, 6),
            Asteroid(3, 6),
            Asteroid(4, 6),
            Asteroid(6, 6),
            Asteroid(8, 6),
            Asteroid(9, 6),
            Asteroid(7, 7),
            Asteroid(4, 8),
            Asteroid(8, 8),
            Asteroid(3, 9),
            Asteroid(6, 9),
            Asteroid(9, 9)
        )
        val result = subject.asteroidMap()
        assertEquals(expected, result)
    }

    @Test
    fun `test foo`() {
        val input = ".#..#\n" +
                    ".....\n" +
                    "#####\n" +
                    "....#\n" +
                    "...##"

        val subject = AsteroidMap(input)
        val expectedX = 3
        val expectedY = 4
        val result = subject.bestAsteroid()
        assertEquals(expectedX, result.x)
        assertEquals(expectedY, result.y)
    }

    @Test
    fun `test map 1`() {
        val input = "......#.#.\n" +
                "#..#.#....\n" +
                "..#######.\n" +
                ".#.#.###..\n" +
                ".#..#.....\n" +
                "..#....#.#\n" +
                "#..#....#.\n" +
                ".##.#..###\n" +
                "##...#..#.\n" +
                ".#....####"
        val subject = AsteroidMap(input)
        val expectedX = 5
        val expectedY = 8
        val result = subject.bestAsteroid()
        assertEquals(expectedX, result.x)
        assertEquals(expectedY, result.y)
    }

    @Test
    fun `test map 2`() {
        val input = "#.#...#.#.\n" +
                ".###....#.\n" +
                ".#....#...\n" +
                "##.#.#.#.#\n" +
                "....#.#.#.\n" +
                ".##..###.#\n" +
                "..#...##..\n" +
                "..##....##\n" +
                "......#...\n" +
                ".####.###."
        val subject = AsteroidMap(input)
        val expectedX = 1
        val expectedY = 2
        val result = subject.bestAsteroid()
        assertEquals(expectedX, result.x)
        assertEquals(expectedY, result.y)
    }

    @Test
    fun `test map 3`() {
        val input = ".#..#..###\n" +
                "####.###.#\n" +
                "....###.#.\n" +
                "..###.##.#\n" +
                "##.##.#.#.\n" +
                "....###..#\n" +
                "..#.#..#.#\n" +
                "#..#.#.###\n" +
                ".##...##.#\n" +
                ".....#.#.."
        val subject = AsteroidMap(input)
        val expectedX = 6
        val expectedY = 3
        val result = subject.bestAsteroid()
        assertEquals(expectedX, result.x)
        assertEquals(expectedY, result.y)
    }

    @Test
    fun `test map 4`() {
        val input = ".#..##.###...#######\n" +
                "##.############..##.\n" +
                ".#.######.########.#\n" +
                ".###.#######.####.#.\n" +
                "#####.##.#.##.###.##\n" +
                "..#####..#.#########\n" +
                "####################\n" +
                "#.####....###.#.#.##\n" +
                "##.#################\n" +
                "#####.##.###..####..\n" +
                "..######..##.#######\n" +
                "####.##.####...##..#\n" +
                ".#####..#.######.###\n" +
                "##...#.##########...\n" +
                "#.##########.#######\n" +
                ".####.#.###.###.#.##\n" +
                "....##.##.###..#####\n" +
                ".#.#.###########.###\n" +
                "#.#.#.#####.####.###\n" +
                "###.##.####.##.#..##"
        val subject = AsteroidMap(input)
        val expectedX = 11
        val expectedY = 13
        val result = subject.bestAsteroid()
        assertEquals(expectedX, result.x)
        assertEquals(expectedY, result.y)
    }

    @Test
    fun `vaporize`() {
        val map = ".#..##.###...#######\n" +
                "##.############..##.\n" +
                ".#.######.########.#\n" +
                ".###.#######.####.#.\n" +
                "#####.##.#.##.###.##\n" +
                "..#####..#.#########\n" +
                "####################\n" +
                "#.####....###.#.#.##\n" +
                "##.#################\n" +
                "#####.##.###..####..\n" +
                "..######..##.#######\n" +
                "####.##.####...##..#\n" +
                ".#####..#.######.###\n" +
                "##...#.##########...\n" +
                "#.##########.#######\n" +
                ".####.#.###.###.#.##\n" +
                "....##.##.###..#####\n" +
                ".#.#.###########.###\n" +
                "#.#.#.#####.####.###\n" +
                "###.##.####.##.#..##"
        val subject = AsteroidMap(map)
        val result = subject.vaporizeList(11, 13)
        assertEquals(Asteroid(11, 12), result[0])
        assertEquals(Asteroid(12, 1), result[1])
        assertEquals(Asteroid(12, 2), result[2])
        assertEquals(Asteroid(12, 8), result[9])
        assertEquals(Asteroid(16, 0), result[19])
        assertEquals(Asteroid(16, 9), result[49])
        assertEquals(Asteroid(11, 1), result[298])
    }
}
