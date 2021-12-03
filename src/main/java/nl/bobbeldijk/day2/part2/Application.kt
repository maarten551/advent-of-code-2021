package nl.bobbeldijk.day2.part2

import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY2))
}

class Application : Answerable<Int> {
    override fun calculateAnswer(input: MutableList<String>): Int {
        var aim = 0;
        var horizontal = 0;
        var depth = 0;

        val commandMapper = mapOf<String, (Int) -> Unit>(
            "forward" to { i -> horizontal += i; depth += (aim * i) },
            "down" to { i -> aim += i },
            "up" to { i -> aim -= i }
        )

        input.forEach { inputLine ->
            val command = inputLine.split(' ');
            commandMapper[command[0]]?.invoke(command[1].toInt())
        }

        println("Horizontal: $horizontal")
        println("Depth: $depth")
        println("---------")
        println("Answer: ${horizontal * depth}")

        return horizontal * depth
    }

}