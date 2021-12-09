package nl.bobbeldijk.day6.part2

import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader
import java.math.BigInteger

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY6))
}

class Application : nl.bobbeldijk.day6.part1.Application(), Answerable<BigInteger> {
    override fun calculateToDay(): ULong {
        return 256u
    }
}