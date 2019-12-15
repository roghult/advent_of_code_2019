import com.jesinkey.aoc.*
import java.io.File

fun readInput(fileName: String, dir: String = "src/main/resources/"): String {
    val fullPath = dir + fileName
    return File(fullPath).readText()
}

fun readInputLines(fileName: String, dir: String = "src/main/resources/"): List<String> {
    val fullPath = dir + fileName
    return File(fullPath).readLines()
}

fun main() {
    val input = readInputLines("input_day_14.txt")
    val subject = Reactions()
    val result = subject.minimumOreForFuel(input)
    println(result)
    // not 303690
}

