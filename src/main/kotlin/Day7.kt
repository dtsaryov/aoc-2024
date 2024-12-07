import java.math.BigDecimal

private val EQUATION_REGEX = Regex("(\\d+):\\s(.*)")

object Day7 {

    private val PART_1_OPS = listOf(BigDecimal::plus, BigDecimal::times)
    private val PART_2_OPS = listOf(
        { n1: BigDecimal, n2: BigDecimal -> n1 + n2 },
        { n1, n2 -> n1 * n2 },
        { n1, n2 -> (n1.toString() + n2.toString()).toBigDecimal() })

    fun part1(inputFileName: String): BigDecimal {
        return readEquations(inputFileName).sumOf { eq ->
            eq.result.takeIf {
                testOperators(BigDecimal.ZERO, 0, PART_1_OPS, eq)
            } ?: BigDecimal.ZERO
        }
    }

    fun part2(inputFileName: String): BigDecimal {
        return readEquations(inputFileName).sumOf { eq ->
            eq.result.takeIf {
                testOperators(BigDecimal.ZERO, 0, PART_2_OPS, eq)
            } ?: BigDecimal.ZERO
        }
    }

    private fun testOperators(
        acc: BigDecimal,
        idx: Int,
        ops: List<(BigDecimal, BigDecimal) -> BigDecimal>,
        eq: Equation
    ): Boolean {
        return ops.any { op ->
            testOperator(acc, idx, op, ops, eq)
        }
    }

    private fun testOperator(
        acc: BigDecimal,
        idx: Int,
        operation: ((BigDecimal, BigDecimal) -> BigDecimal),
        ops: List<(BigDecimal, BigDecimal) -> BigDecimal>,
        eq: Equation
    ): Boolean {
        val newAcc = operation(acc, eq.numbers[idx])

        if (idx == eq.numbers.lastIndex) return newAcc == eq.result

        return testOperators(newAcc, idx + 1, ops, eq)
    }

    private fun readEquations(inputFileName: String): List<Equation> {
        val equations = mutableListOf<Equation>()
        for (line in getInputLines(inputFileName)) {
            val (_, resultString, numbersString) = EQUATION_REGEX.find(line)?.groupValues ?: continue
            val result = resultString.toBigDecimal()
            val numbers = numbersString.split(' ').map(String::toBigDecimal)

            equations += Equation(result, numbers)
        }
        return equations
    }

    private data class Equation(
        val result: BigDecimal,
        val numbers: List<BigDecimal>
    )
}