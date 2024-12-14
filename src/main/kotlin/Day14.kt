private val REGEX = Regex("p=(-?\\d+),(-?\\d++)\\sv=(-?\\d+),(-?\\d+)")

private const val ITERATIONS = 100

object Day14 {

    fun getSafetyFactor(inputFileName: String, n: Int, m: Int): Long {
        val robots = mutableListOf<Point>()

        for (line in getInputLines(inputFileName)) {
            val (_, x, y, dx, dy) = REGEX.find(line)?.groupValues ?: continue
            robots += Point(
                (x.toInt() + dx.toInt() * ITERATIONS).mod(m),
                (y.toInt() + dy.toInt() * ITERATIONS).mod(n)
            )
        }

        val quadrants = IntArray(4)
        for ((x, y) in robots) {
            val xi = if (x < m / 2) 0
            else if (x > m / 2) 1
            else continue

            val yi = if (y < n / 2) 0
            else if (y > n / 2) 1
            else continue

            quadrants[xi * 2 + yi]++
        }

        return quadrants.fold(1L, Long::times)
    }
}