package nl.bobbeldijk.day4

import java.util.stream.IntStream

class Board(numbersInSpot: List<List<String>>) {
    private val spots = Array(25) { BoardSpot(it, null) }

    init {
        IntStream.range(0, 25).forEach {
            spots[it].number = numbersInSpot[it / 5][it % 5].trim().toInt()
        }
    }

    /**
     * Return true if there is a bingo
     */
    fun drawNumber(number: Int): Boolean {
        spots.filter { it.number == number }.forEach { it.isChecked = true }

        return isBingo()
    }

    fun sumUncheckedNumbers(): Int {
        return spots.filter { it.isChecked.not() }.sumOf { it.number!! }
    }

    private fun isBingo(): Boolean {
        return arrayOf(
            {
                IntStream.range(0, 5).anyMatch { row ->
                    IntStream.range(0, 5).allMatch { column -> spots[(row * 5) + column].isChecked }
                }
            }, //horizontalCheck
            {
                IntStream.range(0, 5).anyMatch { row ->
                    IntStream.range(0, 5).allMatch { column -> spots[(column * 5) + row].isChecked }
                }
            } //verticalCheck
        ).any { it() }
    }
}