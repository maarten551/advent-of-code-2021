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
        setup(input)

        for (drawnNumber in drawnNumbers) {
            val boardWithBingo = boards.find { board -> board.drawNumber(drawnNumber) }

            if (boardWithBingo != null) {
                println("sum of all unmarked numbers: ${boardWithBingo.sumUncheckedNumbers()}")
                println("the number that was just called: $drawnNumber")
                println("---------")
                println("Answer: ${boardWithBingo.sumUncheckedNumbers() * drawnNumber}")

                return boardWithBingo.sumUncheckedNumbers() * drawnNumber
            }
        }

        throw AnswerNotFoundException("No bingo was found!")
    }

    fun setup(input: MutableList<String>) {
        drawnNumbers = input.removeAt(0).split(',').map(String::toInt).toList()
        // Remove all empty lines
        input.removeAll { s -> s.trim().isEmpty() }
        // f#ck these double spaces
        input.replaceAll { s -> s.replace(Regex(" {2,}"), " ").trim() }

        // Thanks mubo for the tip!
        boards = input.windowed(5, 5).map { l -> l.map { s -> s.split(' ') } }.map(::Board)
    }

}