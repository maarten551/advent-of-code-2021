package nl.bobbeldijk.day3.part2

import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader
import java.util.stream.IntStream
import kotlin.math.abs

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY3))
}

class Application : Answerable<Int> {
    override fun calculateAnswer(input: MutableList<String>): Int {
        val oxygen = findRating(input) { abs(it - 1) }
        val c02 = findRating(input) { it }

        println("oxygen generator rating: $oxygen")
        println("CO2 scrubber rating: $c02")
        println("---------")
        println("Answer: ${oxygen * c02}")

        return oxygen * c02
    }

    private fun findRating(input: MutableList<String>, removeIfBitIsPipe: (Int) -> Int): Int {
        // Shallow copy
        val copyOfOutput = mutableListOf(*input.toTypedArray())

        while (copyOfOutput.size > 1) {
            IntStream.range(0, input[0].length).forEach { i ->
                when (copyOfOutput.size) {
                    0, 1 -> return@forEach
                }

                val mostCommonBitAtIndex = lookupMostCommonBitOnColumn(copyOfOutput, i)
                copyOfOutput.removeAll { it[i] == removeIfBitIsPipe(mostCommonBitAtIndex).toString()[0] }
            }
        }

        return copyOfOutput[0].toInt(2)
    }

    private fun lookupMostCommonBitOnColumn(input: MutableList<String>, columnIndex: Int): Int {
        return if (input.map { it[columnIndex] }.count { it == '0' } > (input.size / 2)) 0 else 1
    }

}