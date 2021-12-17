package day04

import readInput
import readTestInput
import shouldBe

data class Board(
    val boardPoints: List<BoardPoint>
) {
    fun won() = (0..4).forEach { counter ->
        val rowWon = boardPoints.filter { it.x == counter }.all { it.marked }
        val columnWon = boardPoints.filter { it.y == counter }.all { it.marked }
        if (rowWon || columnWon) return true
    }
        .let { false }

    fun mark(value: Int) {
        boardPoints.filter { it.value == value }
            .forEach { it.marked = true }
    }

    fun calculate(luckyNumber: Int) = luckyNumber * this.boardPoints.filter { !it.marked }.sumOf { it.value }
}

data class BoardPoint(val x: Int, val y: Int, var marked: Boolean, val value: Int)

fun List<String>.toBoard() = Board(
    flatMapIndexed { columnIndex: Int, line: String ->
        line.trim().split(" ")
            .filter { it != "" }
            .mapIndexed { rowIndex, stringNum -> BoardPoint(rowIndex, columnIndex, false, stringNum.toInt()) }
    })

fun main() {
    val day = "04"

    fun List<String>.readFirstInts() =
        this[0].split(",").filter { it != "" }.map { it.toInt() }

    fun part1(input: List<String>): Int {
        val numbers = input.readFirstInts()

        val boards: List<Board> = input.drop(2)
            .windowed(5, 6)
            .map { it.toBoard() }

        numbers.forEach { currentNumber ->
            boards.forEach { it.mark(currentNumber) }
            boards.find { it.won() }?.let {
                return it.calculate(currentNumber)
            }
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        val numbers = input.readFirstInts()

        val boards: List<Board> = input.drop(2)
            .windowed(5, 6)
            .map { it.toBoard() }

        numbers.forEach { currentNumber ->
            boards.forEach { currentBoard ->
                currentBoard.mark(currentNumber)
                if (boards.all { it.won() }) {
                    return currentBoard.calculate(currentNumber)
                }
            }


        }
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readTestInput(day)
    part1(testInput) shouldBe 4512
    part2(testInput) shouldBe 1924

    //real input
    val input = readInput(day)
    part1(input) shouldBe 44088
    part2(input) shouldBe 23670
}