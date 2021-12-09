package nl.bobbeldijk.day6.part1

import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader
import java.math.BigInteger

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY6))
}

open class Application : Answerable<BigInteger> {
    var fishGrowth: MutableMap<UByte, ULong> = mutableMapOf()

    override fun calculateAnswer(input: MutableList<String>): BigInteger {
        applyInput(input)

        repeat(calculateToDay().toInt()) {
            applyDay()
            nextDay()
        }

        println("Answer: ${fishGrowth.values.sum()}")

        return BigInteger.valueOf(fishGrowth.values.sum().toLong())
    }

    private fun applyDay() {
        fishGrowth[0u]?.let { addNumberToFishGrowth(7u, it) }
        fishGrowth[0u]?.let { addNumberToFishGrowth(9u, it) }
    }

    private fun nextDay() {
        fishGrowth.remove(0u)
        fishGrowth = fishGrowth.mapKeys { (it.key - 1u).toUByte() }.toMutableMap()
    }

    private fun applyInput(input: MutableList<String>) {
        input[0].split(',').map { it.toUByte() }.forEach { addNumberToFishGrowth(it, 1u) }
    }

    private fun addNumberToFishGrowth(dayTillChild: UByte, amountOfFish: ULong) {
        fishGrowth.merge(dayTillChild, amountOfFish, ULong::plus)
    }

    protected open fun calculateToDay(): ULong {
        return 80u
    }
}