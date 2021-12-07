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
        parseCommands(input).forEach(::applyCommand)

        println("Answer: ${oceanMap.values.count { it >= 2 }}")

        return oceanMap.values.count { it >= 2 }
    }

    override fun applyCommand(command: ParsedCommand) {
        super.applyCommand(command)

        if (command.x1 != command.x2 && command.y1 != command.y2) {
            val xProgression = createRange(command.x1, command.x2)
            val yProgression = createRange(command.y1, command.y2)

            xProgression.forEachIndexed { i, _ ->
                addToMap(xProgression.elementAt(i), yProgression.elementAt(i))
            }
        }
    }
}