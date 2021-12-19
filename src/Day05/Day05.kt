package Day05

import readInput
import readTestInput
import shouldBe
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

data class Point(val x: Int, val y: Int)

fun main() {
    val day = "05"

    fun part1(input: List<String>) = input.map { it.splitByArrow() }
        .map { it.mapToPairs() }
        .filter { it.first.x == it.second.x || it.first.y == it.second.y }
        .flatMap { point -> pointsVerticalOrHorizontal(point) }.groupingBy { it }.eachCount()
        .filterValues { it >= 2 }.size

    fun part2(input: List<String>) = input.map { it.splitByArrow() }
        .map { it.mapToPairs() }
        .filter { it.first.x == it.second.x || it.first.y == it.second.y || it.arePairDiagonal() }
        .flatMap { point -> point.pointsVerticalHorizontalOrDiagonal() }.groupingBy { it }.eachCount()
        .filterValues { it >= 2 }.size

    // test if implementation meets criteria from the description, like:
    val testInput = readTestInput(day)
    part1(testInput) shouldBe 5
    part2(testInput) shouldBe 12

    //real input
    val input = readInput(day)
    part1(input) shouldBe 6564
    part2(input) shouldBe 19172
}

private fun pointsVerticalOrHorizontal(
    point: Pair<Point, Point>,
): List<Point> {
    val downx = min(point.first.x, point.second.x)
    val downy = min(point.first.y, point.second.y)
    val upx = max(point.first.x, point.second.x)
    val upy = max(point.first.y, point.second.y)

    return (downx..upx).flatMap { x ->
        (downy..upy).map { y ->
            Point(x, y)
        }
    }
}

private fun Pair<Point, Point>.arePairDiagonal() = abs(first.x - second.x) == abs(first.y - second.y)

private fun Pair<Point, Point>.getPointsBetweenDiagonal(): List<Point> {
    val xmark = if (second.x - first.x > 0) {
        1
    } else {
        -1
    }
    val ymark = if (second.y - first.y > 0) {
        1
    } else {
        -1
    }
    val points = mutableSetOf<Point>()
    points.add(first)
    points.add(second)

    var nextPoint = Point(first.x + xmark, first.y + ymark)
    points.add(nextPoint)
    while (nextPoint != second) {
        points.add(nextPoint)
        nextPoint = Point(nextPoint.x + xmark, nextPoint.y + ymark)
    }

    return points.toList()
}

private fun Pair<Point, Point>.pointsVerticalHorizontalOrDiagonal(): List<Point> {
    return if (arePairDiagonal()) {
        return getPointsBetweenDiagonal()
    } else pointsVerticalOrHorizontal(this)
}

private fun List<String>.mapToPairs(): Pair<Point, Point> {
    val firstSplit = this[0].split(",")
    val first = Point(firstSplit[0].toInt(), firstSplit[1].toInt())
    val secondSplit = this[1].split(",")
    val second = Point(secondSplit[0].toInt(), secondSplit[1].toInt())
    return Pair(first, second)
}

private fun String.splitByArrow() = split("->")
    .filter { it != "" }
    .map { it.trim() }