package nl.bobbeldijk.day7.part1

import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY7))
}

open class Application : Answerable<Int> {
    override fun calculateAnswer(input: MutableList<String>): Int {
        val positions = input[0].split(',').map(String::toInt)
        val min = positions.minOf { it }
        val max = positions.maxOf { it }

        val answer = (min until max).minOf { calculateFuel(positions, it) }
        println("Answer: $answer")

        return answer
    }

    protected open fun calculateFuel(positions: List<Int>, goToPosition: Int): Int {
        return positions.sumOf { nl.bobbeldijk.day5.part1.Application.createRange(it, goToPosition).count() - 1 }
    }
}