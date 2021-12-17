package nl.bobbeldijk.day7.part1

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import nl.bobbeldijk.util.Answerable
import nl.bobbeldijk.util.InputFile
import nl.bobbeldijk.util.InputReader
import kotlin.system.measureTimeMillis

fun main() {
    Application().calculateAnswer(InputReader.readListFromInputFile(InputFile.DAY7))
}

open class Application : Answerable<Int> {
    override fun calculateAnswer(input: MutableList<String>): Int {
        var answer = 0;

        val elapsedTime = measureTimeMillis {
            val positions = input[0].split(',').map(String::toInt)
            val min = positions.minOf { it }
            val max = positions.maxOf { it }

            runBlocking {
                answer = (min until max).map { p ->
                    async(Dispatchers.Default) {
                        calculateFuel(positions, p)
                    }
                }.awaitAll().minOf { it }
            }
        }

        println("Elapsed time: $elapsedTime milliseconds")
        println("Answer: $answer")
        return answer
    }

    protected open fun calculateFuel(positions: List<Int>, goToPosition: Int): Int {
        return positions.sumOf { nl.bobbeldijk.day5.part1.Application.createRange(it, goToPosition).count() - 1 }
    }
}