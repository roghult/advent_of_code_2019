package com.jesinkey.aoc

import kotlin.math.absoluteValue

data class Coordinate(
    val x: Int,
    val y: Int
) {
    fun distance(): Int {
        return x.absoluteValue + y.absoluteValue
    }
}

class WireMap {
    fun distanceToClosestCrossingWires(paths: List<String>): Int {
        val allCoordinates = paths.map { pathToCoordinates(it) }
        var crossingCoordinates = crossingCoordinates(allCoordinates)

        val closestCoordinate = crossingCoordinates.minBy { it.distance() }!!
        return closestCoordinate.distance()
    }

    private fun crossingCoordinates(allCoordinates: List<List<Coordinate>>): Set<Coordinate> {
        var crossingCoordinates = allCoordinates[0].toSet()
        for (coordinates in allCoordinates) {
            crossingCoordinates = crossingCoordinates.intersect(coordinates)
        }
        return crossingCoordinates
    }

    private fun pathToCoordinates(path: String): List<Coordinate> {
        val coordinates = mutableListOf(Coordinate(0, 0))
        val movements = path.split(',')
        for (movement in movements) {
            coordinates.addAll(coordinatesFromMovement(coordinates.last(), movement))
        }
        return coordinates.toList() - Coordinate(0, 0)
    }

    private fun coordinatesFromMovement(start: Coordinate, movement: String): List<Coordinate> {
        val steps = movement.substring(1).toInt()
        return (1..steps).map {
            when (movement[0]) {
                'R' -> start.copy(x = start.x + it)
                'L' -> start.copy(x = start.x - it)
                'U' -> start.copy(y = start.y + it)
                'D' -> start.copy(y = start.y - it)
                else -> throw IllegalArgumentException("Movement must start with R, U, D or L")
            }
        }
    }

    fun minStepsToCrossingWires(paths: List<String>): Int {
        val allCoordinates = paths.map { pathToCoordinates(it) }
        var crossingCoordinates = crossingCoordinates(allCoordinates)
        var stepsToCrossingWires = crossingCoordinates.map { crossingCoordinate ->
            allCoordinates.map { it.indexOf(crossingCoordinate) + 1 }.sum()
        }
        return stepsToCrossingWires.min()!!
    }

}
