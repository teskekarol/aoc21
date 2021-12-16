package day01

import readInput
import readTestInput
import shouldBe

fun main() {

    fun part1(input: List<String>): Int = input
        .map { it.toInt() }
        .zipWithNext { a, b -> oneIfRightIsBigger(a, b) }
        .sum()

    fun part2(input: List<String>): Int {
        return input
            .map { it.toInt() }
            .mapIndexedNotNull { index, value -> getValueOfNextThreeValues(value, input, index) }
            .zipWithNext { a, b -> oneIfRightIsBigger(a, b) }.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readTestInput("01")
    part1(testInput) shouldBe 7
    part2(testInput) shouldBe 5

    //real input
    val input = readInput("01")
    part1(input) shouldBe 1184
    part2(input) shouldBe 1158
}

fun oneIfRightIsBigger(left: Int, right: Int) = if (right > left) {
    1
} else {
    0
}

fun getValueOfNextThreeValues(
    value: Int,
    input: List<String>,
    index: Int
) = try {
    value + input[index + 1].toInt() + input[index + 2].toInt()
} catch (e: IndexOutOfBoundsException) {
    null
}