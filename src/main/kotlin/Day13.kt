import kotlin.math.round

private val REGEX_A = Regex("Button A: X\\+(\\d+), Y\\+(\\d+)")
private val REGEX_B = Regex("Button B: X\\+(\\d+), Y\\+(\\d+)")
private val REGEX_PRIZE = Regex("Prize: X=(\\d+), Y=(\\d+)")

private const val A_COST = 3
private const val B_COST = 1

object Day13 {

    fun part1(inputFileName: String): Long {
        var tokens = 0L

        var ax = 0
        var ay = 0
        var bx = 0
        var by = 0

        for (line in getInputLines(inputFileName)) {
            if (REGEX_A.matches(line)) {
                val (_, x, y) = REGEX_A.find(line)?.groupValues!!
                ax = x.toInt()
                ay = y.toInt()
            } else if (REGEX_B.matches(line)) {
                val (_, x, y) = REGEX_B.find(line)?.groupValues!!
                bx = x.toInt()
                by = y.toInt()
            } else if (REGEX_PRIZE.matches(line)) {
                val (_, x, y) = REGEX_PRIZE.find(line)?.groupValues!!

                tokens += solveGame(
                    Game(
                        Point(ax, ay),
                        Point(bx, by),
                        Point(x.toInt(), y.toInt())
                    )
                )
            }
        }

        return tokens
    }

    private fun solveGame(game: Game): Long {
        val adx = game.buttonA.x
        val ady = game.buttonA.y

        val bdx = game.buttonB.x
        val bdy = game.buttonB.y

        val x = game.prize.x
        val y = game.prize.y

        val (a, b) = solveSystem(
            adx.toDouble(), bdx.toDouble(), x.toDouble(),
            ady.toDouble(), bdy.toDouble(), y.toDouble()
        )

        if (round(a) != a || round(b) != b) return 0

        return A_COST * a.toLong() + B_COST * b.toLong()
    }

    private fun solveSystem(
        adx: Double,
        bdx: Double,
        x: Double,
        ady: Double,
        bdy: Double,
        y: Double
    ): Pair<Double, Double> {
        val det = adx * bdy - bdx * ady

        if (det == 0.0) throw IllegalArgumentException("No solution")

        val a = (x * bdy - bdx * y) / det
        val b = (adx * y - x * ady) / det

        return a to b
    }
}

private class Game(val buttonA: Point, val buttonB: Point, val prize: Point)