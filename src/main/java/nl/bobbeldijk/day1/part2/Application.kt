package nl.bobbeldijk.day1.part2

import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader
import java.util.stream.IntStream

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY1))
}

class Application : Answerable<Int> {
    override fun calculateAnswer(input: MutableList<String>): Int {
        var count = 0;
        val calculateThreeMeasurementWindowFromIndex = { index: Int ->
            IntStream.range(index, index + 3)
                .map { i -> input[i].toInt() }
                .sum()
        }

        IntStream.range(0, input.size - 2)
            .map(calculateThreeMeasurementWindowFromIndex)
            .reduce { previousDepth, currentDepth ->
                if (previousDepth < currentDepth) {
                    count++
                }

                currentDepth
            }

        println("Answer: $count")
        return count;
    }

}