import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

object Day8 {

    fun part1(inputFileName: String): Int {
        val (input, size) = readInput(inputFileName)
        val (n, m) = size

        val antinodes = mutableSetOf<Point>()

        for ((_, nodes) in input.entries) {
            for (n1 in nodes) {
                for (n2 in nodes) {
                    if (n1 == n2) continue

                    antinodes += getAntinodes(n1, n2, n, m)
                }
            }
        }

        return antinodes.size
    }

    private fun getAntinodes(p0: Point, p1: Point, n: Int, m: Int): Set<Point> {
        val antinodes = mutableSetOf<Point>()

        when {
            // vertical line
            p0.x == p1.x -> {
                antinodes += Point(p0.x, getPrevCoord(p0.y, p1.y))
                antinodes += Point(p0.x, getNextCoord(p0.y, p1.y))
            }
            // horizontal line
            p0.y == p1.y -> {
                antinodes += Point(getPrevCoord(p0.x, p1.x), p0.y)
                antinodes += Point(getNextCoord(p0.x, p1.x), p0.y)
            }

            else -> {
                val xPrev = 2 * p0.x - p1.x
                val yPrev = 2 * p0.y - p1.y

                val xNext = 2 * p1.x - p0.x
                val yNext = 2 * p1.y - p0.y

                antinodes += Point(xPrev, yPrev)
                antinodes += Point(xNext, yNext)
            }
        }

        return antinodes.filter { (x, y) ->
            x in 0..<n && y in 0..<m
        }.toSet()
    }

    private fun getPrevCoord(c0: Int, c1: Int): Int = min(c0, c1) - abs(c0 - c1)

    private fun getNextCoord(c0: Int, c1: Int): Int = max(c0, c1) + abs(c0 - c1)

    private fun readInput(inputFileName: String)
            : Pair<MutableMap<Char, MutableList<Point>>, Pair<Int, Int>> {
        val n: Int
        val m: Int

        val input = getInputLines(inputFileName)

        n = input.size
        m = input[0].length

        val map = mutableMapOf<Char, MutableList<Point>>()
        for ((i, line) in input.withIndex()) {
            for ((j, c) in line.withIndex()) {
                if (c == '.') continue

                map.compute(c) { _, coords ->
                    if (coords == null) {
                        mutableListOf(Point(j, i))
                    } else {
                        coords += Point(j, i)
                        coords
                    }
                }
            }
        }
        return map to (n to m)
    }

    private data class Point(val x: Int, val y: Int)
}