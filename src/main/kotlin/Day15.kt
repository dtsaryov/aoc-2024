private const val ROBOT = '@'
private const val SPACE = '.'
private const val BOX = 'O'
private const val WALL = '#'

object Day15 {

    fun part1(inputFileName: String): Long {
        var (map, moves, robot) = readInput(inputFileName)

        for (move in moves) {
            var (nextX, nextY) = getDirection(move) + robot
            when (map[nextY][nextX]) {
                SPACE -> {
                    map[robot.y][robot.x] = SPACE
                    map[nextY][nextX] = ROBOT
                    robot = Point(nextX, nextY)
                }

                WALL -> {}
                BOX -> {
                    val toMove = mutableListOf<Point>()
                    toMove += robot

                    do {
                        (getDirection(move) + Point(nextX, nextY)).apply {
                            toMove += Point(nextX, nextY)

                            nextX = x
                            nextY = y
                        }
                    } while (map[nextY][nextX] != WALL && map[nextY][nextX] != SPACE)

                    if (map[nextY][nextX] == SPACE) {
                        for (obj in toMove.drop(1).reversed()) {
                            map[nextY][nextX] = map[obj.y][obj.x]
                            nextX = obj.x
                            nextY = obj.y
                        }
                        map[robot.y][robot.x] = SPACE
                        map[nextY][nextX] = ROBOT
                        robot = Point(nextX, nextY)
                    }
                }
            }
        }

        var sum = 0L

        for (i in 1..<map.lastIndex) {
            for (j in 1..<map[i].lastIndex) {
                if (map[i][j] == BOX) sum += (i * 100L + j)
            }
        }

        return sum
    }

    private fun getDirection(move: Char): Point {
        return when (move) {
            '<' -> Point(-1, 0)
            '>' -> Point(1, 0)
            '^' -> Point(0, -1)
            'v' -> Point(0, 1)
            else -> error("Unknown move: $move")
        }
    }

    private operator fun Point.plus(p: Point): Point = Point(x + p.x, y + p.y)

    private operator fun Point.times(n: Int): Point = Point(x * n, y * n)

    private fun readInput(inputFileName: String): Triple<Array<CharArray>, List<Char>, Point> {
        val input = getInputLines(inputFileName)

        var split = 0
        var robotPos: Point? = null

        val map = mutableListOf<CharArray>()

        for (i in 0..input.lastIndex) {
            val line = input[i]

            if (line.isEmpty()) {
                split = i + 1
                break
            }

            val array = CharArray(input[i].length)
            for (j in 0..line.lastIndex) {
                val c = line[j]
                if (c == ROBOT) {
                    robotPos = Point(j, i)
                } else {
                    array[j] = c
                }
            }
            map += array
        }

        val moves = mutableListOf<Char>()
        for (i in split..input.lastIndex) {
            val line = input[i]
            moves.addAll(line.toList())
        }

        return Triple(map.toTypedArray(), moves, robotPos!!)
    }

    @Suppress("unused")
    private fun getPresentableMap(map: Array<CharArray>): String {
        val sb = StringBuilder()
        for (line in map) {
            sb.appendLine(line.concatToString())
        }
        return sb.toString()
    }
}