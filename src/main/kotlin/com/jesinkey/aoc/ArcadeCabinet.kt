package com.jesinkey.aoc

data class LongCoordinate(
    val x: Long,
    val y: Long
)

class ArcadeCabinet() {

    private fun movePaddle(currentX: Long, desiredX: Long?): String {
        return when {
            desiredX == null -> "0"
            currentX < desiredX -> "1"
            currentX == desiredX -> "0"
            currentX > desiredX -> "-1"
            else -> TODO()
        }
    }

    private fun ballPos(screen: MutableMap<LongCoordinate, Char>) =
        screen.filterValues { it == 'O' }.map { it.key }.first()

    fun padelPos(screen: MutableMap<LongCoordinate, Char>) =
        screen.filterValues { it == 'P' }.map { it.key }.first()

    fun play(sequence: List<Long>) {
        var hitAllBalls = false
        while (!hitAllBalls) {
            var lost = false
            val sequenceMap = sequenceListToMap(sequence)
            sequenceMap[0] = 2
            val intcode = Intcode(sequenceMap)
            var score = 0L
            var readLine = "1"
            val screen = mutableMapOf<LongCoordinate, Char>()
            while (!lost) {
                val joystick = readLine.toLong()
                val inputs = mutableListOf(joystick)
                while (true) {
                    val x = intcode.run(inputs) ?: break
                    val y = intcode.run()!!
                    val tileCode = intcode.run()!!

                    val coordinate = LongCoordinate(x, y)
                    if (coordinate.x == -1L && coordinate.y == 0L) {
                        score = tileCode
                    } else {
                        val tile = tile(tileCode)
                        screen[coordinate] = tile
                    }
                }

                printScreen(screen)

                val desiredX = ballPos(screen).x
                val paddlePos = padelPos(screen)
                readLine = movePaddle(paddlePos.x, desiredX)
                val bLeft = screen.values.filter { it == 'B' }.size
                if (bLeft == 0) {
                    hitAllBalls = true
                    lost = true
                    println("##################################")
                    println("##################################")
                    println("##################################")
                    println("##################################")
                    println("$bLeft balls left. Score = $score")
                    println("##################################")
                    println("##################################")
                    println("##################################")
                    println("##################################")
                }
                Thread.sleep(20)
            }
        }
    }

    private fun printScreen(screen: MutableMap<LongCoordinate, Char>) {
        val largestX = screen.keys.maxBy { it.x }!!.x
        val largestY = screen.keys.maxBy { it.y }!!.y
        val smallestX = screen.keys.minBy { it.x }!!.x
        val smallestY = screen.keys.minBy { it.y }!!.y
        val yRange = (smallestY..largestY).toList()
        val xRange = (smallestX..largestX).toList()
        val mapList = yRange.map { y ->
            xRange.map { x ->
                val pos = LongCoordinate(x, y)
                if (screen.containsKey(pos)) {
                    screen[pos].toString()
                } else {
                    " "
                }
            }
        }
        println()
        println()
        for (row in mapList) {
            row.map {
                print("$it")
            }
            println()
        }
        println()
    }

    private fun tile(tileCode: Long): Char {
        return when (tileCode) {
            0L -> ' '
            1L -> 'W'
            2L -> 'B'
            3L -> 'P'
            4L -> 'O'
            else -> TODO()
        }
    }


}
