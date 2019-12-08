import com.jesinkey.aoc.*
import java.io.File

fun main() {
    val inputFileName = "input_day_8.txt"
    val dir = "src/main/resources/"
    val fullPath = dir + inputFileName
    val input = File(fullPath).readLines().first()
    val image = Image(input, 25, 6)
    val message = image.finalImage()
    println(message.replace("0", " "))
}
