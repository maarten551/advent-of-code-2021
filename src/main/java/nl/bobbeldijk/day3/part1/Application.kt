package nl.bobbeldijk.day3.part1

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
        var gammaRate = 0
        var epsilonRate = 0

        IntStream.range(0, input[0].length).forEach { i ->
            gammaRate += lookupMostCommonBitOnColumn(input, i) shl input[0].length - (i + 1)
            epsilonRate += abs(lookupMostCommonBitOnColumn(input, i) - 1) shl input[0].length - (i + 1)
        }

        println("gamma rate: $gammaRate")
        println("epsilon rate: $epsilonRate")
        println("---------")
        println("Answer: ${gammaRate * epsilonRate}")

        return gammaRate * epsilonRate
    }

    private fun lookupMostCommonBitOnColumn(input: MutableList<String>, columnIndex: Int): Int {
        return if (input.map { line -> line[columnIndex] }.count('0'::equals) > (input.size / 2)) 0 else 1
    }

}