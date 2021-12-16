package day00

import readInput
import readTestInput
import shouldBe

fun main() {
    val day = "00"

    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readTestInput(day)
    part1(testInput) shouldBe 0
    part2(testInput) shouldBe 0

    //real input
    val input = readInput(day)
    part1(input) shouldBe 0
    part2(input) shouldBe 0
}