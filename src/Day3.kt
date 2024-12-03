import java.io.File

private val REGEX = Regex("mul\\((\\d+),(\\d+)\\)")

fun main() {
    println(part1())
}

// 1,606,724,68
private fun part1(): Int {
    val input = getInputText("day3_input.txt")

    return REGEX.findAll(input).map { match ->
        val (_, n1, n2) = match.groupValues
        n1.toInt() * n2.toInt()
    }.sum()
}

private fun getInputText(fileName: String): String {
    return object {}.javaClass.getResource(fileName)?.let { url ->
        File(url.toURI()).readText()
    }.orEmpty()
}