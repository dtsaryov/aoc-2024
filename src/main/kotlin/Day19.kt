object Day19 {

    fun part1(inputFileName: String): Long {
        val input = getInputLines(inputFileName)

        val patterns = input[0].split(',').map { it.trim() }
        val targets = input.drop(2)

        return targets.sumOf { s ->
            if (hasMatchingPatternSet(s, patterns)) 1L
            else 0L
        }
    }

    private fun hasMatchingPatternSet(s: String, patterns: List<String>): Boolean {
        return patterns.firstOrNull { p ->
            when {
                s.isEmpty() -> true
                s.startsWith(p) -> hasMatchingPatternSet(s.substringAfter(p), patterns)
                else -> false
            }
        } != null
    }

    fun part2(inputFileName: String): Long {
        val input = getInputLines(inputFileName)

        val patterns = input[0].split(',').map { it.trim() }
        val targets = input.drop(2)

        val cache = mutableMapOf<String, Long>()

        return targets.sumOf { s ->
            countVariants(s, patterns, cache)
        }
    }

    private fun countVariants(
        s: String,
        patterns: List<String>,
        cache: MutableMap<String, Long>
    ): Long {
        var result = cache[s]
        if (result != null) return result

        result = patterns.sumOf { p ->
            when {
                s == p -> 1
                s.startsWith(p) -> countVariants(s.substringAfter(p), patterns, cache)
                else -> 0
            }
        }

        cache[s] = result

        return result
    }
}