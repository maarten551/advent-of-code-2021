package nl.bobbeldijk.day5.part1

import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY5))
}

data class ParsedCommand(val x1: Int, val y1: Int, val x2: Int, val y2: Int)
data class Position(val x: Int, val y: Int)

open class Application : Answerable<Int> {
    val oceanMap: MutableMap<Position, Int> = mutableMapOf()
    val addToMap = { x: Int, y: Int -> oceanMap.compute(Position(x, y)) { _, i -> if (i == null) 1 else i + 1 } }
    val parseInputRegex = Regex("(\\d+),(\\d+) -> (\\d+),(\\d+)")

    override fun calculateAnswer(input: MutableList<String>): Int {
        parseCommands(input)
            .filter { it.x1 == it.x2 || it.y1 == it.y2 }
            .forEach(::applyHorizontalOrVerticalCommands)

        println("Answer: ${oceanMap.values.count { it >= 2 }}")

        return oceanMap.values.count { it >= 2 }
    }

    protected fun parseCommands(input: MutableList<String>): List<ParsedCommand> {
        return input.map { parseInputRegex.find(it)!!.groupValues }
            .map { ParsedCommand(it[1].toInt(), it[2].toInt(), it[3].toInt(), it[4].toInt()) }
    }

    protected fun applyHorizontalOrVerticalCommands(command: ParsedCommand) {
        addToMap(command.x1, command.y1) // first point always gets mapped
        (command.x2 downTo command.x1 + 1).forEach { x -> addToMap(x, command.y1) }
        (command.x2 until command.x1).forEach { x -> addToMap(x, command.y1) }
        (command.y2 downTo command.y1 + 1).forEach { y -> addToMap(command.x1, y) }
        (command.y2 until command.y1).forEach { y -> addToMap(command.x1, y) }
    }


}