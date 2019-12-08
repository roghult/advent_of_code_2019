import com.jesinkey.aoc.*
import java.io.File

fun main() {
    val inputFileName = "input_day_8.txt"
    val dir = "src/main/resources/"
    val fullPath = dir + inputFileName
    val input = File(fullPath).readLines().first()
    val image = Image(input, 25, 6)
    val layerWithLeast0 = image.layerWithLeast(0)
    val occurrences1 = image.occurrencesInLayer(layerWithLeast0, 1)
    val occurrences2 = image.occurrencesInLayer(layerWithLeast0, 2)
    println(occurrences1)
    println(occurrences2)
    println(occurrences1 * occurrences2)
}
