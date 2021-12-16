import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(dayNumber: String) = read("src/day$dayNumber", "Day$dayNumber.txt")
fun readTestInput(dayNumber: String) = read("src/day$dayNumber", "Day${dayNumber}_test.txt")
private fun read(parent: String, fileName: String) = File(parent, fileName).readLines()

infix fun Int.shouldBe(expected: Int) {
    if(this == expected) {
        return
    } else {
        println("Expected $this to be equal $expected")
        throw IllegalStateException()
    }
}

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
