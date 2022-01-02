package nl.bobbeldijk.day8.part2

import nl.bobbeldijk.day8.ParsedInputLine
import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY8))
}

/**
 * #DISCLAIMER!!!
 *
 * I was not smart enough to solve this logic, so I stole the logic of just using the segment length from this guy:
 * https://github.com/mubaarakhassan
 *
 * I did however refactor it a bit, so it's more inline with the solution of part 1 (from the same day)
 * If you want you to see how I failed, see the previous commit of this file (https://github.com/maarten551/advent-of-code-2021/commit/b04aea48a93ae9bdbf9a6eb5020b7f10cc17ce21#diff-00b0a6eace348c7e4f93f985ce0d7d56db05eaff4250bbc85b706c10a3a2a3f4)
 */
class Application : nl.bobbeldijk.day8.part1.Application(), Answerable<Int> {
    override fun calculateAnswer(input: MutableList<String>): Int {
        val parsedInput = parseInput(input)

        val answer = parsedInput.map(::determineMapping)
            .map { it.replace(" ", "").replace(",", "") }
            .sumOf { it.toInt() }

        println("Answer: $answer")

        return answer
    }

    private fun determineMapping(parsedInputLine: ParsedInputLine): String {
        return parsedInputLine.outputSegments.map {
            when (it.length) {
                2 -> "1"
                3 -> "7"
                4 -> "4"
                7 -> "8"
                5 -> {
                    // 2 and 5 needs to have 1 same characters for 1 if not it is 3
                    // 2 needs to have 2 same characters for 4 if not it is 5
                    if (getSegmentByLength(parsedInputLine, 2).filter { a -> it.contains(a) }.length == 1) {
                        if (getSegmentByLength(parsedInputLine, 4).filter { a -> it.contains(a) }.length == 2) {
                            "2"
                        } else {
                            "5"
                        }
                    } else {
                        "3"
                    }
                }
                else -> { // when the length is 6
                    // 0 and 9 needs to have 2 same characters for 1 if not it is 6
                    // 0 needs to have 3 same characters for 4 if not it is 9
                    if (getSegmentByLength(parsedInputLine, 2).filter { a -> it.contains(a) }.length == 2) {
                        if (getSegmentByLength(parsedInputLine, 4).filter { a -> it.contains(a) }.length == 3) {
                            "0"
                        } else {
                            "9"
                        }
                    } else {
                        "6"
                    }
                }
            }
        }.joinToString { it }
    }

    private fun getSegmentByLength(parsedInputLine: ParsedInputLine, length: Int): String {
        return parsedInputLine.segments.find { it.length == length }!!
    }
}