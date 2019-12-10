import com.jesinkey.aoc.*
import java.io.File

fun main() {
    val inputFileName = "input_day_10.txt"
    val dir = "src/main/resources/"
    val fullPath = dir + inputFileName
    val input = File(fullPath).readText()
    val map = AsteroidMap(input)
    val bestAsteroid = map.bestAsteroid()
    println("Best asteroid $bestAsteroid sees ${bestAsteroid.spottedAsteroids.size} other asteroids.")

    val destroyedAsteroids = map.vaporizeList(bestAsteroid.x, bestAsteroid.y)
    val wanted = 199
    val destroyedAsteroid = destroyedAsteroids[wanted]
    val answer = destroyedAsteroid.x * 100 + destroyedAsteroid.y
    println("Number $wanted to be destroyed was $destroyedAsteroid")
    println("Answer = ${answer}")
}
