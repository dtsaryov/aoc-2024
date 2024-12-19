object Day19 {

    fun part1(inputFileName: String): Long {
        val input = getInputLines(inputFileName)

        val patterns = input[0].split(',').map { it.trim() }
        val targets = input.drop(2)

        return targets.sumOf { s ->
            if (findNextPattern(s, patterns)) 1L
            else 0L
        }
    }

    private fun findNextPattern(s: String, patterns: List<String>): Boolean {
        return patterns.firstOrNull { p ->
            when {
                s.isEmpty() -> true
                s.startsWith(p) -> findNextPattern(s.substringAfter(p), patterns)
                else -> false
            }
        } != null
    }
}