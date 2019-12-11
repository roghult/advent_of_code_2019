package com.jesinkey.aoc

const val WIDTH = 99999999L
const val HEIGHT = 99999999L
const val BLACK = 0
const val WHITE = 1

enum class Color(val code: Int) {
    BLACK(com.jesinkey.aoc.BLACK),
    WHITE(com.jesinkey.aoc.WHITE);

    companion object {
        fun getByValue(value: Int) = values().first { it.code == value }
        fun getByValue(value: Char) = getByValue(Character.getNumericValue(value))
    }
}

enum class Turn(val code: Int) {
    LEFT(0),
    RIGHT(1);

    companion object {
        fun getByValue(value: Int) = values().first { it.code == value }
        fun getByValue(value: Char) = getByValue(Character.getNumericValue(value))
        fun getByValue(value: Long) = getByValue(value.toInt())
    }
}

fun mod(a: Int, b: Int): Int {
    var x = a
    while (x < 0) {
        x += b
    }
    return x % b
}

enum class RobotDirection(val code: Int) {
    UP(0),
    RIGHT(1),
    DOWN(2),
    LEFT(3);

    companion object {
        fun getByValue(value: Int): RobotDirection = values().first { it.code == value }
        fun getByValue(value: Char): RobotDirection = getByValue(Character.getNumericValue(value))
        fun newDirection(currentDirection: RobotDirection, turn: Turn): RobotDirection {
            return when (turn) {
                Turn.LEFT -> getByValue(mod(currentDirection.code - 1, 4))
                Turn.RIGHT -> getByValue(mod(currentDirection.code + 1, 4))
            }
        }
    }
}

data class Position(val x: Long, val y: Long) {

    fun move(direction: RobotDirection): Position {
        return when (direction) {
            RobotDirection.UP -> Position(x, y + 1)
            RobotDirection.RIGHT -> Position(x + 1, y)
            RobotDirection.DOWN -> Position(x, y - 1)
            RobotDirection.LEFT -> Position(x - 1, y)
        }
    }

    override fun toString(): String {
        return "($x, $y)"
    }

}

class PaintRobot(val intcode: Intcode) {

    fun createMap(): MutableMap<Position, Int> {
        return mutableMapOf()
//        return (0..HEIGHT).map { (0..WIDTH).map { BLACK }.toMutableList() }.toMutableList()
    }

    fun run(): Int {
        println("Starting")
        var paintedTiles = 0
        val map = createMap()
        var position = Position(0, 0)
        var direction = RobotDirection.UP
        while (true) {
            println("New run")

            val currentColor = map.getOrElse(position) {
                paintedTiles++
                0
            }
            val inputs = mutableListOf(currentColor.toLong())
            val output = intcode.run(inputs)
            if (output == null) {
                printMap(map)
                return paintedTiles
            }

            val colorToPaint = output?.toInt()
            val turnOutput = intcode.run()!!
            val turn = Turn.getByValue(turnOutput)

            println("Painting $position $currentColor to $colorToPaint")
            map[position] = colorToPaint
            direction = RobotDirection.newDirection(direction, turn)

            println("New direction = $direction")
            println()

//            printMap(map)

            position = position.move(direction)
        }
    }

    private fun printMap(map: MutableMap<Position, Int>) {
        val largestX = map.keys.maxBy { it.x }!!.x
        val largestY = map.keys.maxBy { it.y }!!.y
        val smallestX = map.keys.minBy { it.x }!!.x
        val smallestY = map.keys.minBy { it.y }!!.y
        val yRange = (smallestY..largestY).toList()
        val xRange = (smallestX..largestX).toList()
        val mapList = yRange.map { y ->
            xRange.map { x ->
                val pos = Position(x, y)
                if (map.containsKey(pos)) {
                    map[pos].toString()
                } else {
                    " "
                }
            }
        }
        println()
        println()
        for (row in mapList) {
            row.map { print("$it") }
            println()
        }
        println()
    }
}