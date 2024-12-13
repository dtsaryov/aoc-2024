import java.math.BigDecimal
import kotlin.math.round

private val REGEX_A = Regex("Button A: X\\+(\\d+), Y\\+(\\d+)")
private val REGEX_B = Regex("Button B: X\\+(\\d+), Y\\+(\\d+)")
private val REGEX_PRIZE = Regex("Prize: X=(\\d+), Y=(\\d+)")

private const val A_COST = 3
private const val B_COST = 1

private val PART_2_ADD = BigDecimal(10000000000000)

object Day13 {

    fun part1(inputFileName: String): Long = countTokens(inputFileName, BigDecimal.ZERO)

    fun part2(inputFileName: String): Long = countTokens(inputFileName, PART_2_ADD)

    private fun countTokens(inputFileName: String, prizeDelta: BigDecimal): Long {
        var tokens = 0L

        for (gameSet in getInputLines(inputFileName).windowed(3, 4)) {
            val (_, adx, ady) = REGEX_A.find(gameSet[0])?.groupValues!!
            val a = BigPoint(adx.toBigDecimal(), ady.toBigDecimal())

            val (_, bdx, bdy) = REGEX_B.find(gameSet[1])?.groupValues!!
            val b = BigPoint(bdx.toBigDecimal(), bdy.toBigDecimal())

            val (_, x, y) = REGEX_PRIZE.find(gameSet[2])?.groupValues!!
            val prize = BigPoint(x.toBigDecimal() + prizeDelta, y.toBigDecimal() + prizeDelta)

            tokens += solveGame(Game(a, b, prize))
        }

        return tokens
    }

    private fun solveGame(game: Game): Long {
        val (a, b) = solveSystem(
            game.a.x.toDouble(), game.b.x.toDouble(), game.prize.x.toDouble(),
            game.a.y.toDouble(), game.b.y.toDouble(), game.prize.y.toDouble()
        )

        if (round(a) != a || round(b) != b) return 0

        return A_COST * a.toLong() + B_COST * b.toLong()
    }

    private fun solveSystem(
        adx: Double, bdx: Double, x: Double,
        ady: Double, bdy: Double, y: Double
    ): Pair<Double, Double> {
        val det = adx * bdy - bdx * ady
        if (det == 0.0) throw IllegalArgumentException("No solution")

        val a = (x * bdy - bdx * y) / det
        val b = (adx * y - x * ady) / det

        return a to b
    }
}

private class Game(val a: BigPoint, val b: BigPoint, val prize: BigPoint)

private data class BigPoint(val x: BigDecimal, val y: BigDecimal)