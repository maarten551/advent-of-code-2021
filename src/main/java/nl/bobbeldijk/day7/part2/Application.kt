package nl.bobbeldijk.day7.part2

import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY7))
}

class Application : nl.bobbeldijk.day7.part1.Application(), Answerable<Int> {
    override fun calculateFuel(positions: List<Int>, goToPosition: Int): Int {
        return positions.sumOf {
            nl.bobbeldijk.day5.part1.Application.createRange(it, goToPosition)
                .mapIndexed { index, _ -> index + 1 }
                .reversed()
                .drop(1)
                .sum()
        }
    }
}