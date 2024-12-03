import kotlin.math.abs

private val regex = Regex("(\\d+)\\s+(\\d+)")

object Day1 {

    fun part1(): Int {
        val lines = getInputLines("day1_input.txt")

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

        return total
    }

    fun part2(): Int {
        val input = getInputLines("day1_input.txt")

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

        return similarity
    }
}
