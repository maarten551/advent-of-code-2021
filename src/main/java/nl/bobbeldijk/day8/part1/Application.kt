package nl.bobbeldijk.day8.part1

import nl.bobbeldijk.day8.ParsedInputLine
import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY8))
}

val segmentsToNumber = mapOf(
    Pair(0, "abcefg"),
    Pair(1, "cf"),
    Pair(2, "acdeg"),
    Pair(3, "acdfg"),
    Pair(4, "bcdf"),
    Pair(5, "abdfg"),
    Pair(6, "abdefg"),
    Pair(7, "acf"),
    Pair(8, "abcdefg"),
    Pair(9, "abcdfg"),
)

open class Application : Answerable<Int> {
    override fun calculateAnswer(input: MutableList<String>): Int {
        val parsedInput = parseInput(input)
        val allowedLengths = listOf(segmentsToNumber[1]!!.length, segmentsToNumber[4]!!.length, segmentsToNumber[7]!!.length, segmentsToNumber[8]!!.length)

        val answer = parsedInput
            .flatMap { it.outputSegments }
            .map { it.length }
            .count(allowedLengths::contains)

        println("Answer: $answer")
        return answer
    }

    protected fun parseInput(input: MutableList<String>): List<ParsedInputLine> {
        val parseLine: (String) -> ParsedInputLine = {
            val pipeSegments = it.split(" | ")
            val scrambleSegments = pipeSegments[0].split(' ').map { s -> String(s.toCharArray().sorted().toCharArray()) }
            val outputSegments = pipeSegments[1].split(' ').map { s -> String(s.toCharArray().sorted().toCharArray()) }

            ParsedInputLine(scrambleSegments, outputSegments)
        }

        return input.map(parseLine)
    }
}