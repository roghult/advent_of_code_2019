import com.jesinkey.aoc.*
import java.io.File

private fun readInput(fileName: String): String {
    val dir = "src/main/resources/"
    val fullPath = dir + fileName
    return File(fullPath).readText()
}

fun main() {
    val moons = listOf(
        Moon(x=1, y=-4, z=3),
        Moon(x=-14, y=9, z=-4),
        Moon(x=-4, y=-6, z=7),
        Moon(x=6, y=-9, z=-11)
    )
    val moonMotion = MoonMotion()
    val stepsToSamePosition = moonMotion.stepsUntilSamePosition(moons)

    println(stepsToSamePosition)
}

