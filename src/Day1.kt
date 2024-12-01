import java.io.File
import kotlin.math.abs
import kotlin.system.measureNanoTime

val regex = Regex("(\\d+)\\s+(\\d+)")

fun main() {
    measureTime {
        part2()
    }
}

// "20,373,490" is the correct answer. 🐢
private fun part2() {
    val input = getInput()

    val left = ArrayList<Int>(1000)
    val right = mutableMapOf<Int, Int>()

    for (line in input) {
        val gv = regex.find(line)?.groupValues ?: continue

        left += gv[1].toInt()

        val rightNum = gv[2].toInt()
        val occurrences = right[rightNum] ?: 0
        right[rightNum] = occurrences + 1
    }

    var similarity = 0

    for (n in left) {
        similarity += n * (right[n] ?: 0)
    }

    println(similarity)
}

// "1,722,302" is the correct answer 🐢
@Suppress("unused")
private fun part1() {
    val lines = getInput()

    val left = ArrayList<Int>(1000)
    val right = ArrayList<Int>(1000)

    for (line in lines) {
        val gv = regex.find(line)?.groupValues ?: continue

        left += gv[1].toInt()
        right += gv[2].toInt()
    }

    left.sort()
    right.sort()

    var total = 0

    for (i in 0 until left.size) {
        val leftMin = left[i]
        val rightMin = right[i]

        total += abs(leftMin - rightMin)
    }

    println(total)
}

private fun getInput(): List<String> {
    val lines = object {}.javaClass.getResource("day1_input.txt")?.let { url ->
        File(url.toURI()).readLines()
    }.orEmpty()
    return lines
}

private inline fun measureTime(block: () -> Unit) {
    val time = measureNanoTime {
        block()
    }
    println("Time: ${time / 1_000} micro seconds")
}
