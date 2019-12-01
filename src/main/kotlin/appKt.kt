import com.jesinkey.aoc.FuelCounterUpper
import com.jesinkey.aoc.MODULES
import com.jesinkey.aoc.Module

fun main(args: Array<String>) {
    val x = FuelCounterUpper()

    val foo = MODULES.sumBy {
        x.requiredFuel(Module(it))
    }
    print(foo)
}
