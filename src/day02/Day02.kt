package day02

import readInput
import readTestInput
import shouldBe

data class Submarine(
    var position: Int,
    var depth: Int
) {
    fun processCommand(submarineCommand: SubmarineCommand) {
        when (submarineCommand.command) {
            SubmarineCommandEnum.FORWARD -> {
                position += submarineCommand.value
            }
            SubmarineCommandEnum.UP -> {
                depth -= submarineCommand.value
            }
            SubmarineCommandEnum.DOWN -> {
                depth += submarineCommand.value
            }
        }
    }
}

data class Submarine2(
    var position: Int,
    var depth: Int,
    var aim: Int
) {
    fun processCommand(submarineCommand: SubmarineCommand) {
        when (submarineCommand.command) {
            SubmarineCommandEnum.FORWARD -> {
                position += submarineCommand.value
                depth += submarineCommand.value * aim
            }
            SubmarineCommandEnum.UP -> {
                aim -= submarineCommand.value
            }
            SubmarineCommandEnum.DOWN -> {
                aim += submarineCommand.value
            }
        }
    }
}

data class SubmarineCommand(val command: SubmarineCommandEnum, val value: Int)

enum class SubmarineCommandEnum {
    FORWARD, UP, DOWN
}

fun main() {
    val day = "02"

    fun part1(input: List<String>): Int = Submarine(0, 0)
        .also { submarine ->
            input.map { it.toSubmarineCommand() }
                .forEach { submarine.processCommand(it) }
        }
        .let { it.depth * it.position }

    fun part2(input: List<String>): Int = Submarine2(0, 0, 0)
        .also { submarine ->
            input.map { it.toSubmarineCommand() }
                .forEach { submarine.processCommand(it) }
        }
        .let { it.depth * it.position }

    // test if implementation meets criteria from the description, like:
    val testInput = readTestInput(day)
    part1(testInput) shouldBe 150
    part2(testInput) shouldBe 900

    //real input
    val input = readInput(day)
    part1(input) shouldBe 1813801
    part2(input) shouldBe 1960569556
}

private fun String.toSubmarineCommand(): SubmarineCommand {
    val splitted = this.split(" ")
    return SubmarineCommand(SubmarineCommandEnum.valueOf(splitted[0].uppercase()), splitted[1].toInt())
}
