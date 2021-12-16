package day03

import readInput
import readTestInput
import shouldBe
import kotlin.math.abs

private fun List<Int>.asInt() = Integer.parseInt(this.joinToString("", "", ""), 2)

fun main() {
    val day = "03"

    fun part1(input: List<String>): Int {
        val numbers = input.map { it.split("").filter { it != "" }.map { it.toInt() } }

        val result = part1solver(numbers)
        val gamma = result.asInt()
        val epsilon = result.map { abs(it - 1) }.asInt()
        return gamma * epsilon
    }

    fun part2(input: List<String>): Int {
        val numbers = input.map { it.split("").filter { it != "" }.map { it.toInt() } }

        val oxygen = part2solver(numbers, true)
            .first()
            .asInt()

        val co2 = part2solver(numbers, false)
            .first()
            .asInt()
        return oxygen * co2
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readTestInput(day)
    part1(testInput) shouldBe 198
    part2(testInput) shouldBe 230

    //real input
    val input = readInput(day)
    part1(input) shouldBe 2724524
    part2(input) shouldBe 2775870
}

fun part2solver(numbers: List<List<Int>>, preferOnes: Boolean): List<List<Int>> {
    var mutableCollection = numbers
    var counter = 0
    while (mutableCollection.size > 1) {
        val elementsFromCounter = mutableCollection.map { it[counter] }
        val zeros = elementsFromCounter.count { it == 0 }
        val ones = elementsFromCounter.count { it == 1 }
        val mostCommonValue = when (preferOnes) {
            true -> {
                if (zeros > ones) {
                    0
                } else {
                    1
                }
            }
            false -> {
                if (zeros > ones) {
                    1
                } else {
                    0
                }
            }
        }

        mutableCollection = mutableCollection.filter { it[counter] == mostCommonValue }
        counter++
    }
    return mutableCollection
}

fun part1solver(numbers: List<List<Int>>) =
    (0 until numbers.first().size).map { counter ->
        val elementsFromCounter = numbers.map { it[counter] }
        val zeros = elementsFromCounter.count { it == 0 }
        val ones = elementsFromCounter.count { it == 1 }
        if (zeros > ones) {
            0
        } else {
            1
        }
    }
        .toList()