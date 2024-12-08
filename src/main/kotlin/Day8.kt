object Day8 {

    fun part1(inputFileName: String): Int {
        val (input, size) = readInput(inputFileName)
        val (n, m) = size

        val antinodes = mutableSetOf<Point>()

        for ((_, nodes) in input.entries) {
            for (p0 in nodes) {
                for (p1 in nodes) {
                    if (p0 == p1) continue

                    antinodes += getAntinodes(p0, p1, n, m)
                }
            }
        }

        return antinodes.size
    }

    fun part2(inputFileName: String): Int {
        val (input, size) = readInput(inputFileName)
        val (n, m) = size

        val antinodes = mutableSetOf<Point>()

        for ((_, nodes) in input.entries) {
            for (p0 in nodes) {
                for (p1 in nodes) {
                    if (p0 == p1) continue

                    antinodes += getAntinodes2(p0, p1, n, m)
                }
            }
        }

        return antinodes.size
    }

    private fun getAntinodes(p0: Point, p1: Point, n: Int, m: Int): Set<Point> {
        val antinodes = mutableSetOf<Point>()

        val xPrev = 2 * p0.x - p1.x
        val yPrev = 2 * p0.y - p1.y

        if (checkRange(xPrev, yPrev, n, m))
            antinodes += Point(xPrev, yPrev)

        val xNext = 2 * p1.x - p0.x
        val yNext = 2 * p1.y - p0.y

        if (checkRange(xNext, yNext, n, m))
            antinodes += Point(xNext, yNext)

        return antinodes
    }

    private fun getAntinodes2(p0: Point, p1: Point, n: Int, m: Int): Set<Point> {
        val antinodes = mutableSetOf<Point>()

        val dx = p0.x - p1.x
        val dy = p0.y - p1.y

        var k = 0
        while (true) {
            val x = p0.x + k * dx
            val y = p0.y + k * dy
            k++

            if (!checkRange(x, y, n, m)) break

            antinodes += Point(x, y)
        }

        return antinodes
    }

    private fun checkRange(x: Int, y: Int, n: Int, m: Int) = x in 0..<n && y in 0..<m

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