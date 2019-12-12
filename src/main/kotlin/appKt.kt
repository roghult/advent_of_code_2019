import com.jesinkey.aoc.*
import java.io.File

fun main() {
    val moons = listOf(
        Moon(1, -4, 3),
        Moon(-14, 9, -4),
        Moon(-4, -6, 7),
        Moon(6, -9, -11)
    )
    val moonMotion = MoonMotion()
//    val moonsAfterSteps = moonMotion.simulate(moons, 1000)
//    val totalEnergy = moonsAfterSteps.sumBy { it.totalEnergy() }
//    println(totalEnergy)

    val stepsToSamePosition = moonMotion.stepsUntilSamePosition(moons)
    println(stepsToSamePosition)
}
