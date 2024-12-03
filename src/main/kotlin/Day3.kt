private val DO_REGEX = Regex("do\\(\\)")
private val DONT_REGEX = Regex("don't\\(\\)")
private val MUL_REGEX = Regex("mul\\((\\d+),(\\d+)\\)")

object Day3 {

    fun part1(): Int {
        return MUL_REGEX
            .findAll(getInputText("day3_input.txt"))
            .sumOf { match ->
                val (_, n1, n2) = match.groupValues
                n1.toInt() * n2.toInt()
            }
    }

    fun part2(): Int {
        val input = getInputText("day3_input.txt")

        var result = 0

        var mulIdx = 0
        var doIdx = 0
        var dontIdx = 0

        while (mulIdx < input.length) {
            val match = MUL_REGEX.find(input, doIdx) ?: break
            mulIdx = match.range.last

            if (dontIdx == 0 || // in the beginning
                mulIdx < dontIdx || // "don't" is ahead
                dontIdx < doIdx // "do" found after "don't"
            ) {
                val (_, n1, n2) = match.groupValues
                result += n1.toInt() * n2.toInt()

                doIdx = mulIdx // move forward
                dontIdx = DONT_REGEX.find(input, mulIdx)?.range?.first ?: continue
            } else {
                doIdx = DO_REGEX.find(input, dontIdx)?.range?.first ?: break
                dontIdx = DONT_REGEX.find(input, doIdx)?.range?.first ?: continue
            }
        }

        return result
    }
}