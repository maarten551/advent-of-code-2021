package nl.bobbeldijk.day5.part2

import nl.bobbeldijk.day5.part1.ParsedCommand
import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY5))
}

class Application : nl.bobbeldijk.day5.part1.Application(), Answerable<Int> {
    override fun calculateAnswer(input: MutableList<String>): Int {
        val groupedCommands = parseCommands(input).groupBy { it.x1 == it.x2 || it.y1 == it.y2 }
        groupedCommands[true]?.forEach(::applyHorizontalOrVerticalCommands)
        groupedCommands[false]?.forEach(::applyDiagonalCommand)

        println("Answer: ${oceanMap.values.count { it >= 2 }}")

        return oceanMap.values.count { it >= 2 }
    }

    private fun applyDiagonalCommand(command: ParsedCommand) {
        val xProgression = createRange(command.x1, command.x2)
        val yProgression = createRange(command.y1, command.y2)

        xProgression.forEachIndexed { i, _ ->
            addToMap(xProgression.elementAt(i), yProgression.elementAt(i))
        }
    }
}