import com.jesinkey.aoc.*
import java.io.File

fun main() {
    val inputFileName = "input_day_13.txt"
    val input = readInput(inputFileName)
    val sequence = input.split(",").map { it.toLong() }
    val arcadeCabinet = ArcadeCabinet()
    arcadeCabinet.play(sequence)
}

private fun readInput(fileName: String): String {
    val dir = "src/main/resources/"
    val fullPath = dir + fileName
    return File(fullPath).readText()
}