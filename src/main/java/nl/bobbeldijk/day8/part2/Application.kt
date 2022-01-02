package nl.bobbeldijk.day8.part2

import nl.bobbeldijk.day8.ParsedInputLine
import nl.bobbeldijk.day8.part1.segmentsToNumber
import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader
import java.util.concurrent.atomic.AtomicBoolean

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY8))
}

class LineDetermination(
    val parsedLine: ParsedInputLine,
    var determineMap: Map<Char, List<Char>>,
    var foundNumbers: Map<String, Int>,
    val stop: AtomicBoolean = AtomicBoolean(false)
) {
    fun clone(): LineDetermination {
        return LineDetermination(parsedLine, determineMap, foundNumbers, stop)
    }
}

class Application : nl.bobbeldijk.day8.part1.Application(), Answerable<Int> {
    private val optionsByLength = segmentsToNumber.entries.groupBy { it.value.length }.map { Pair(it.key, it.value.map { p -> p.key }.toList()) }.toMap()

    override fun calculateAnswer(input: MutableList<String>): Int {
        val parsedInput = parseInput(input).map { LineDetermination(it, createDefaultDeterminationMapping(), mutableMapOf()) }

        determineMapping(parsedInput[0])

        return 0
    }

    private fun determineMapping(lineDetermination: LineDetermination): Map<Char, List<Char>> {
        val mapping = createDefaultDeterminationMapping()

        applyUniqueLengthCharacters(lineDetermination);
        val outputSegments = lineDetermination.parsedLine.outputSegments.filter { !lineDetermination.foundNumbers.containsKey(it) }


//        lineDetermination.parsedLine.allSegments.forEach {applyUniqueLengthCharacters(lineDetermination, it)}

        return mapping
    }

    private fun bruteforce(lineDetermination: LineDetermination, startAtIndex: Int): LineDetermination? {
        // Stop if found
        if (lineDetermination.stop.get()) {
            return null;
        }

        val notFoundSegments = lineDetermination.parsedLine.outputSegments.filter { !lineDetermination.foundNumbers.containsKey(it) }
        if (notFoundSegments.isEmpty()) {
            lineDetermination.stop.set(true);
            return lineDetermination;
        }

        val segment = notFoundSegments[startAtIndex]!!
        val possibleOptions = optionsByLength[segment.length]!!

//        (0 .. possibleOptions.size)
//            .filter { option -> segmentsToNumber[option]!!.all {  } }

//        optionsByLength[segment.length]!!
//            .filter { segment.all { lineDetermination.determineMap } }


//        val isPossible = segment.all {  }


        return null;
    }

    private fun applyUniqueLengthCharacters(lineDetermination: LineDetermination) {
        lineDetermination.parsedLine.segments.forEach { segment ->
            val possibleOptions = optionsByLength[segment.length]!!
            if (possibleOptions.size == 1) {
                val originalMapping = segmentsToNumber[possibleOptions[0]]!!

                originalMapping.forEach { ogLetter ->
                    lineDetermination.determineMap += Pair(ogLetter, lineDetermination.determineMap[ogLetter]!!.filter { segment.contains(it) })
                    lineDetermination.foundNumbers += Pair(segment, possibleOptions[0])
                }
            }
        }
    }

    private fun createDefaultDeterminationMapping(): Map<Char, List<Char>> {
        val pairs = ('a'..'g').map { key ->
            Pair(key, ('a'..'g').toList())
        }

        return mapOf(*pairs.toTypedArray())
    }
}