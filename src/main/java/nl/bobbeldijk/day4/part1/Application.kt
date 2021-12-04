package nl.bobbeldijk.day4.part1

import nl.bobbeldijk.day4.Board
import nl.bobbeldijk.util.AnswerNotFoundException
import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY4))
}

open class Application : Answerable<Int> {
    var drawnNumbers: List<Int> = listOf()
    var boards: List<Board> = listOf()

    override fun calculateAnswer(input: MutableList<String>): Int {
        prepareInput(input)

        for (drawnNumber in drawnNumbers) {
            boards.find { board -> board.drawNumber(drawnNumber) }?.also {
                println("sum of all unmarked numbers: ${it.sumUncheckedNumbers()}")
                println("the number that was just called: $drawnNumber")
                println("---------")
                println("Answer: ${it.sumUncheckedNumbers() * drawnNumber}")

                return it.sumUncheckedNumbers() * drawnNumber
            }
        }

        throw AnswerNotFoundException("No bingo was found!")
    }

    fun prepareInput(input: MutableList<String>) {
        drawnNumbers = input.removeAt(0).split(',').map(String::toInt).toList()
        // Remove all empty lines
        input.removeAll { it.trim().isEmpty() }
        // f#ck these double spaces
        input.replaceAll { it.replace(Regex(" {2,}"), " ").trim() }

        // Thanks mubo for the tip!
        boards = input.windowed(5, 5).map { line -> line.map { it.split(' ') } }.map(::Board)
    }

}