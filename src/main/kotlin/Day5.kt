private val RULE_REGEX = Regex("\\d+\\|\\d+")

object Day5 {

    fun part1(inputFileName: String): Int {
        val (before, updates) = getRulesAndUpdates(inputFileName)
        var result = 0
        for (line in updates) {
            var correct = true
            for (i in line.indices)
                if (!checkAfter(i, line, before)) {
                    correct = false
                    break
                }

            if (correct)
                result += line[line.lastIndex / 2]
        }
        return result
    }

    fun part2(inputFileName: String): Int {
        val (valuesBeforeKey, updates) = getRulesAndUpdates(inputFileName)

        var result = 0

        for (line in updates) {
            var correct = true
            for (i in line.indices) {
                if (!checkAfter(i, line, valuesBeforeKey)) {
                    correct = false
                    break
                }
            }

            if (!correct) {
                val sorted = line.sortedWith { n1, n2 ->
                    val beforeN2 = valuesBeforeKey[n2] ?: emptySet()
                    if (n1 in beforeN2) -1 else 1
                }
                val num = sorted[sorted.lastIndex / 2]
                result += num
            }
        }

        return result
    }

    private fun getRulesAndUpdates(inputFileName: String): Pair<MutableMap<Int, Set<Int>>, MutableList<List<Int>>> {
        val rules = mutableMapOf<Int, Set<Int>>() // values – numbers before a key

        val updates = mutableListOf<List<Int>>()

        for (line in getInputLines(inputFileName)) {
            if (RULE_REGEX.matches(line)) {
                val (s1, s2) = line.split('|')
                val (n1, n2) = s1.toInt() to s2.toInt() // before, after

                rules[n2] = rules.getOrDefault(n2, emptySet()) + n1
            } else if (line.isEmpty()) {
                continue
            } else {
                updates += line.split(',').map(String::toInt)
            }
        }

        return rules to updates
    }

    // check no numbers before that should be after
    private fun checkAfter(i: Int, line: List<Int>, before: Map<Int, Set<Int>>): Boolean {
        val n = line[i]
        for (j in i + 1..line.lastIndex) {
            val rulesBefore = before[n] ?: emptySet()
            if (line[j] in rulesBefore)
                return false
        }
        return true
    }
}