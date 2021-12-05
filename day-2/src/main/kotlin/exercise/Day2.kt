package exercise

import java.io.File

class Day2 {

    fun part1(lines: Sequence<String>) {
        lines
            .map { it.split(" ") }
            .map { (command, amount) -> command to amount.toInt() }
            .fold( Triple(0, 0, 0)) { (depth, horizontal, _), (command, amount) ->
                when (command) {
                    "forward" -> Triple(depth, horizontal + amount, 0)
                    "up" -> Triple(depth - amount, horizontal, 0)
                    "down" -> Triple(depth + amount, horizontal, 0)
                    else -> Triple(depth, horizontal, 0)
                }
            }
            .let { (depth, horizontal, _) -> println("Answer is: ${depth * horizontal}") }
    }

    fun part2(lines: Sequence<String>) {
        lines
            .map { it.split(" ") }
            .map { (command, amount) -> command to amount.toInt() }
            .fold( Triple(0, 0, 0)) { (depth, horizontal, aim), (command, amount) ->
                when (command) {
                    "forward" -> Triple(depth + aim * amount, horizontal + amount, aim)
                    "up" -> Triple(depth, horizontal, aim - amount)
                    "down" -> Triple(depth, horizontal, aim + amount)
                    else -> Triple(depth, horizontal, aim)
                }
            }
            .let { (depth, horizontal, _) -> println("Answer 2 is: ${depth * horizontal}") }
    }

}

fun main() {
    File("./executor/src/main/resources/day-2.txt")
        .useLines {
            Day2().part1(it)
        }
    File("./executor/src/main/resources/day-2.txt")
        .useLines {
            Day2().part2(it)
        }
}
