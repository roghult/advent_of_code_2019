import com.jesinkey.aoc.*
import java.io.File

fun main() {
    val inputFileName = "input_day_9.txt"
    val dir = "src/main/resources/"
    val fullPath = dir + inputFileName
    val sequence = File(fullPath).readLines().first().split(",").map { it.toLong() }
//    val sequence = listOf(109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99)
//    val sequence = listOf(1102,34915192,34915192,7,4,7,99,0)
//    val sequence = listOf(104,1125899906842624,99)
    val intcode = Intcode(sequence.mapIndexed { index, value -> index.toLong() to value }.toMap() as MutableMap<Long, Long>)
    var output: Long? = 0
    while (output != null) {
        output = intcode.run(mutableListOf(2))
    }
}
