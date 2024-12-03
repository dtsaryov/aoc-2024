import java.io.File

private val DO_REGEX = Regex("do\\(\\)")
private val DONT_REGEX = Regex("don't\\(\\)")
private val MUL_REGEX = Regex("mul\\((\\d+),(\\d+)\\)")

fun main() {
    print(part2())
}

// 1,606,724,68
@Suppress("unused")
private fun part1(): Int {
    val input = getInputText("day3_input.txt")

    return MUL_REGEX.findAll(input).map { match ->
        val (_, n1, n2) = match.groupValues
        n1.toInt() * n2.toInt()
    }.sum()
}

// 84,893,551
private fun part2(testInput: String? = null): Int {
    val input = testInput ?: getInputText("day3_input.txt")

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

@Suppress("SameParameterValue")
private fun getInputText(fileName: String): String {
    return object {}.javaClass.getResource(fileName)?.let { url ->
        File(url.toURI()).readText()
    }.orEmpty()
}