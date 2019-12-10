import com.jesinkey.aoc.*
import java.io.File

fun main() {
    val inputFileName = "input_day_10.txt"
    val dir = "src/main/resources/"
    val fullPath = dir + inputFileName
    val input = File(fullPath).readText()
    val map = AsteroidMap(input)
    val bestAsteroid = map.bestAsteroid()
    println(bestAsteroid.spottedAsteroids.size)
}
