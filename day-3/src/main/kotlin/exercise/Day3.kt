package exercise

import java.io.File

fun String.explode(): List<Int> {
    return this.toCharArray().map { it.digitToInt() }
}
fun Boolean.toInt() = if (this) 1 else 0

class Day3 {

    fun part1(input: Sequence<String>): Int {
        var grouped = input
            .map { it.explode().withIndex().map { it.index to it.value } }
            .flatMap { it }
            .groupBy(keySelector = { it.first }, valueTransform = { it.second })
            .values

        var gammaRate = grouped.map { (it.count{ it == 1 } > (it.size / 2)).toInt() }
            .joinToString("")
        var epsilonRate = grouped.map { (it.count{ it == 1 } < (it.size / 2)).toInt() }
            .joinToString("")

        return gammaRate.toInt(2) * epsilonRate.toInt(2)
    }
}

fun main() {
    File("./executor/src/main/resources/day-3.txt")
        .useLines {
            check(Day3().part1(it) == 1082324)
        }
}
