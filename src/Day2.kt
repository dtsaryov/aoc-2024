import java.io.File
import kotlin.math.abs

private const val MAX_DELTA = 3

fun main() {
    println(part2())
}

// 269
@Suppress("unused")
private fun part1(): Int {
    return getInput().count {
        isSafe(
            it.split(' ').map(String::toInt))
    }
}

// please don't look at it…
private fun part2(): Int {
    return getInput().count { line ->
        val numbers = line.split(' ').map(String::toInt)

        var hasSafe = false
        for (idx in numbers.indices) {
            if (isSafe(getWithout(numbers, idx))) {
                hasSafe = true
                break
            }
        }
        hasSafe
    }
}

private fun getWithout(numbers: List<Int>, withoutIdx: Int): List<Int> {
    if (withoutIdx == 0) return numbers.subList(1, numbers.size)
    if (withoutIdx == numbers.size - 1) return numbers.subList(0, numbers.size - 1)
    return numbers.subList(0, withoutIdx) + numbers.subList(withoutIdx + 1, numbers.size)
}

private fun isSafe(numbers: List<Int>): Boolean {
    if (numbers.size <= 2) return true

    var safe = true
    var predicate: ((Int, Int) -> Boolean)? = null

    for (i in 0 until numbers.size - 1) {
        if (predicate == null) {
            predicate = getPredicate(numbers[i], numbers[i + 1])
        }

        safe = predicate(numbers[i], numbers[i + 1])
        if (!safe) break
    }

    return safe
}

private fun getPredicate(first: Int, second: Int): (Int, Int) -> Boolean {
    return if (first < second) {
        { a, b -> a < b && abs(a - b) <= MAX_DELTA }
    } else if (first > second) {
        { a, b -> a > b && abs(a - b) <= MAX_DELTA }
    } else {
        { _, _ -> false }
    }
}

private fun getInput(): List<String> {
    val lines = object {}.javaClass.getResource("day2_input.txt")?.let { url ->
        File(url.toURI()).readLines()
    }.orEmpty()
    return lines
}