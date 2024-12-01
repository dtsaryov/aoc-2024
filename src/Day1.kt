import java.io.File
import kotlin.math.abs
import kotlin.system.measureNanoTime

val regex = Regex("(\\d+)\\s+(\\d+)")

fun main() {
    measureTime {
        part1Solution()
    }
}

private inline fun measureTime(block: () -> Unit) {
    val time = measureNanoTime {
        block()
    }
    println("Time: ${time / 1_000} micro seconds")
}

// "1,722,302" is the correct answer 🐢
private fun part1Solution() {
    val lines = object {}.javaClass.getResource("day1_input.txt")?.let { url ->
        File(url.toURI()).readLines()
    }.orEmpty()

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