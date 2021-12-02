package nl.bobbeldijk.day1.part1

import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY1))
}

class Application : Answerable<Int> {
    override fun calculateAnswer(input: MutableList<String>): Int {
        var count = 0;

        input.map(Integer::parseInt)
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