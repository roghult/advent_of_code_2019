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
            val spottedAsteroids = mutableListOf<Pair<Asteroid, K>>()
            for (otherAsteroid in asteroidMap) {
                if (otherAsteroid == asteroid) {
                    continue
                }

                val k = calcK(otherAsteroid, asteroid)
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
            asteroid.spottedAsteroids = spottedAsteroids.map { it.first }
        }
        return asteroidMap.maxBy { it.spottedAsteroids.size }!!
    }

    private fun calcK(asteroid1: Asteroid, asteroid2: Asteroid): K {
        val deltaY = asteroid1.y - asteroid2.y
        val deltaX = asteroid1.x - asteroid2.x.toDouble()
        val kValue = if (deltaY == 0) { // probably not needed?
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
        return K(kValue, direction)
    }
}

enum class Direction(val code: Int) {
    NORTH_WEST(0),
    NORTH_EAST(1),
    SOUTH_EAST(2),
    SOUTH_WEST(3)
}

data class K(val k: Double, val direction: Direction)