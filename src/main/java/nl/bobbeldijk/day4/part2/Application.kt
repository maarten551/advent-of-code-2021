package nl.bobbeldijk.day4.part2

import nl.bobbeldijk.util.AnswerNotFoundException
import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY4))
}

class Application : nl.bobbeldijk.day4.part1.Application(), Answerable<Int> {
    override fun calculateAnswer(input: MutableList<String>): Int {
        prepareInput(input)

        for (drawnNumber in drawnNumbers) {
            val boardsThatBingo = boards.filter { board -> board.drawNumber(drawnNumber) }
            boards = boards.minus(boardsThatBingo.toSet())

            if (boards.isEmpty()) {
                println("sum of all unmarked numbers: ${boardsThatBingo[0].sumUncheckedNumbers()}")
                println("the number that was just called: $drawnNumber")
                println("---------")
                println("Answer: ${boardsThatBingo[0].sumUncheckedNumbers() * drawnNumber}")

                return boardsThatBingo[0].sumUncheckedNumbers() * drawnNumber
            }
        }

        throw AnswerNotFoundException("No bingo was found!")
    }

}