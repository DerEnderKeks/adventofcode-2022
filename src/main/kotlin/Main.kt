import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val day = args.first().toInt()

    val dayFun = when (day) {
        1 -> ::d01
        2 -> ::d02
        3 -> ::d03
        4 -> ::d04
        else -> null
    }

    if (dayFun == null) {
        println("Day $day is not implemented.")
        exitProcess(1)
    } else {
        val input = File("inputs/${"%02d".format(day)}.txt").readText()

        println("Day $day:")
        dayFun(input)
    }
}