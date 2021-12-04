package nl.bobbeldijk.day4

import java.util.stream.IntStream

class Board(numbersInSpot: List<List<String>>) {
    val spots = Array(25) { i -> BoardSpot(i, null) }

    init {
        IntStream.range(0, 25).forEach { indexAtBoard ->
            spots[indexAtBoard].number = numbersInSpot[indexAtBoard / 5][indexAtBoard % 5].trim().toInt()
        }
    }

    /**
     * Return true if there is a bingo
     */
    fun drawNumber(number: Int): Boolean {
        spots.filter { s -> s.number == number }.forEach { s -> s.isChecked = true }

        return isBingo()
    }

    fun sumUncheckedNumbers(): Int {
        return spots.filter { s -> s.isChecked.not() }.sumOf { s -> s.number!! }
    }

    private fun isBingo(): Boolean {
        return arrayOf(
            { IntStream.range(0, 5).anyMatch { row -> IntStream.range(0, 5).allMatch { column -> spots[(row * 5) + column].isChecked } } }, //horizontalCheck
            { IntStream.range(0, 5).anyMatch { row -> IntStream.range(0, 5).allMatch { column -> spots[(column * 5) + row].isChecked } } } //verticalCheck
        ).any { checkFun -> checkFun() }
    }
}