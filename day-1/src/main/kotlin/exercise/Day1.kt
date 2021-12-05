package exercise

import java.io.File

class Day1 {

    fun part1(): Day1 {
        File("./executor/src/main/resources/day-1.txt")
            .useLines { lines ->
                val amountIncreases = lines.map { it.toInt() }
                    .windowed(2)
                    .filter { it[0] < it[1] }
                    .count()

                println("Total increases: $amountIncreases")
            }
        return this
    }

    fun part2() {
        File("./executor/src/main/resources/day-1.txt")
            .useLines { lines ->
                val amountIncreases = lines.map { it.toInt() }
                    .windowed(3)
                    .map { it.sum() }
                    .windowed(2)
                    .filter { it[0] < it[1] }
                    .count()

                println("Total windows increases: $amountIncreases")
            }
    }

}

fun main() {
    Day1()
        .part1()
        .part2()
}
