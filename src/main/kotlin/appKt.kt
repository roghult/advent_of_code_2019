import com.jesinkey.aoc.*
import java.io.File

fun main() {
    val inputFileName = "input_day_13.txt"
    val input = readInput(inputFileName)
    val sequence = input.split(",").map { it.toLong() }
    val sequenceMap = sequenceListToMap(sequence)
    val intcode = Intcode(sequenceMap)
    var output: Long? = 0
    var counter = 0
    while (output != null) {
        output = intcode.run()
        output = intcode.run()
        output = intcode.run()

        if (output == 2L) {
            counter++
        }
    }

    println(counter)
}

private fun readInput(fileName: String): String {
    val dir = "src/main/resources/"
    val fullPath = dir + fileName
    return File(fullPath).readText()
}