import com.jesinkey.aoc.*
import java.io.File

fun main() {
    val inputFileName = "input_day_11.txt"
    val dir = "src/main/resources/"
    val fullPath = dir + inputFileName
    val input = File(fullPath).readText()

    val sequence = File(fullPath).readLines().first().split(",").map { it.toLong() }
    val sequenceMap = sequenceListToMap(sequence)
    val intcode = Intcode(sequenceMap)
    val paintRobot = PaintRobot(intcode)
    val paintedTiles = paintRobot.run()
    println(paintedTiles)

}
