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

    override fun calculateAnswer(input: MutableList<String>): Int {
        parseCommands(input)
            .filter { it.x1 == it.x2 || it.y1 == it.y2 }
            .forEach(::applyCommand)

        println("Answer: ${oceanMap.values.count { it >= 2 }}")

        return oceanMap.values.count { it >= 2 }
    }

    protected fun parseCommands(input: MutableList<String>): List<ParsedCommand> {
        val parseInputRegex = Regex("(\\d+),(\\d+) -> (\\d+),(\\d+)")
        return input.map { parseInputRegex.find(it)!!.groupValues }
            .map { ParsedCommand(it[1].toInt(), it[2].toInt(), it[3].toInt(), it[4].toInt()) }
    }

    protected open fun applyCommand(command: ParsedCommand) {
        if (command.y1 == command.y2) {
            createRange(command.x1, command.x2).forEach { x -> addToMap(x, command.y1) }
        } else if (command.x1 == command.x2) {
            createRange(command.y1, command.y2).forEach { y -> addToMap(command.x1, y) }
        }
    }

    companion object Util {
        // A single default range creator can't handle dynamic low-to-high and high-to-low, so this function switches it automatically
        fun createRange(i1: Int, i2: Int): IntProgression {
            return if (i1 <= i2) (i1..i2) else (i1 downTo i2)
        }
    }
}