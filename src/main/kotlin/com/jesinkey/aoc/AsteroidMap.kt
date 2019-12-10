package com.jesinkey.aoc

class AsteroidMap(val map: String) {

    fun asteroidMap(): List<Asteroid> {
        val width = map.indexOf('\n')

        val asteroidMap = map.filter { it != '\n' }.mapIndexed { index, c ->
            if (c == '#') {
                val x = index % width
                val y = index / width
                Asteroid(x = x, y = y)
            } else {
                null
            }
        }.filterNotNull()
        return asteroidMap
    }

    fun bestAsteroid(): Asteroid {
        val asteroidMap = asteroidMap()
        for (asteroid in asteroidMap) {
            val spottedAsteroids = findAsteroids(asteroidMap, asteroid)
            asteroid.spottedAsteroids = spottedAsteroids.toList()
        }
        return asteroidMap.maxBy { it.spottedAsteroids.size }!!
    }

    fun vaporizeList(startX: Int, startY: Int): List<Asteroid> {
        val asteroidMap = asteroidMap().toMutableList()
        val startingAsteroid = asteroidMap.single { it.x == startX && it.y == startY }
        asteroidMap.remove(startingAsteroid)
        val directionOrder = listOf(
            Direction.NORTH_EAST,
            Direction.SOUTH_EAST,
            Direction.SOUTH_WEST,
            Direction.NORTH_WEST
        )

        val destroyedAsteroids = mutableListOf<Asteroid>()
        while (asteroidMap.isNotEmpty()) {
            val foundAsteroids = findAsteroids(asteroidMap, startingAsteroid)
            for (direction in directionOrder) {
                val asteroidsInDirection = foundAsteroids.filter { (asteroid, k) ->
                    k.direction == direction
                }.sortedBy { it.second.slope }.toMutableList()
                val asteroidRightAbove = asteroidsInDirection.singleOrNull { it.second.slope == 0.0 }
                if (asteroidRightAbove != null) {
                    asteroidsInDirection.remove(asteroidRightAbove)
                    asteroidsInDirection.add(0, asteroidRightAbove)
                }
                for (asteroidInDirection in asteroidsInDirection) {
                    destroyedAsteroids.add(asteroidInDirection.first)
                    asteroidMap.remove(asteroidInDirection.first)
                }
            }
        }
        return destroyedAsteroids
    }

    private fun findAsteroids(asteroids: List<Asteroid>, asteroid: Asteroid): List<Pair<Asteroid, AsteroidView>> {
        val spottedAsteroids = mutableListOf<Pair<Asteroid, AsteroidView>>()
        for (otherAsteroid in asteroids) {
            if (otherAsteroid == asteroid) {
                continue
            }
            val k = asteroidView(otherAsteroid, asteroid)
            if (spottedAsteroids.any { it.second == k }) {
                val alreadySpottedAsteroid = spottedAsteroids.single { it.second == k }
                if (asteroid.distance(alreadySpottedAsteroid.first) > asteroid.distance(otherAsteroid)) {
                    spottedAsteroids.removeIf { it.second == k }
                    spottedAsteroids.add(otherAsteroid to k)
                }
            } else {
                spottedAsteroids.add(otherAsteroid to k)
            }
        }
        return spottedAsteroids
    }

    private fun asteroidView(asteroid1: Asteroid, asteroid2: Asteroid): AsteroidView {
        val deltaY = asteroid1.y - asteroid2.y
        val deltaX = asteroid1.x - asteroid2.x.toDouble()
        val slope = if (deltaY == 0) { // probably not needed?
            if (deltaX < 0) {
                -0.0000000001
            } else {
                0.0000000001
            }
        } else {
            if (deltaX == 0.0) {
                0.0
            } else {
                deltaY / deltaX
            }
        }
        val direction = when {
            asteroid1.y >= asteroid2.y && asteroid1.x >= asteroid2.x -> Direction.SOUTH_EAST
            asteroid1.y >= asteroid2.y && asteroid1.x <= asteroid2.x -> Direction.SOUTH_WEST
            asteroid1.y <= asteroid2.y && asteroid1.x >= asteroid2.x -> Direction.NORTH_EAST
            asteroid1.y <= asteroid2.y && asteroid1.x <= asteroid2.x -> Direction.NORTH_WEST
            else -> TODO()
        }
        return AsteroidView(slope, direction)
    }
}

enum class Direction(val code: Int) {
    NORTH_WEST(0),
    NORTH_EAST(1),
    SOUTH_EAST(2),
    SOUTH_WEST(3)
}

data class AsteroidView(val slope: Double, val direction: Direction)