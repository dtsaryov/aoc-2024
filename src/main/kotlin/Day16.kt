import java.util.*

private const val START = 'S'
private const val END = 'E'
private const val WALL = '#'

private const val ROTATION_COST = 1000

private val DIRECTIONS: Pair<IntArray, IntArray> = Pair(
    intArrayOf(0, 1, 0, -1), // Y
    intArrayOf(1, 0, -1, 0)  // X
)

private data class Path(
    val x: Int,
    val y: Int,
    val direction: Int,
    val cost: Int
) : Comparable<Path> {
    override fun compareTo(other: Path): Int = cost - other.cost
}

object Day16 {

    fun part1(inputFileName: String): Int {
        val (maze, start) = readInput(inputFileName)

        return searchPath(maze, start)
    }

    private fun searchPath(maze: Array<CharArray>, start: Point): Int {
        val (yDirections, xDirections) = DIRECTIONS

        val (startY, startX) = start

        val n = maze.size
        val m = maze[0].size

        val costs = Array(m) { Array(n) { IntArray(4) { Int.MAX_VALUE } } }
        // 3d matrix of locations and directions
        val visited = Array(m) { Array(n) { BooleanArray(4) } }

        val queue = PriorityQueue<Path>()
        fun enq(i: Int, j: Int, d: Int, c: Int) {
            if (costs[i][j][d] <= c || maze[i][j] == WALL) return

            costs[i][j][d] = c

            queue.add(Path(j, i, d, c))
        }

        enq(startX, startY, 0, 0)

        while (true) {
            val (j, i, d, cost) = queue.remove()
                ?: error("No more paths")

            if (visited[i][j][d]) continue
            visited[i][j][d] = true

            if (maze[i][j] == END) {
                return cost
            }

            // same direction
            enq(i + yDirections[d], j + xDirections[d], d, cost + 1)
            // rotate left
            enq(i, j, (d + 1) % 4, cost + ROTATION_COST)
            // rotate right
            enq(i, j, (d + 3) % 4, cost + ROTATION_COST)
        }
    }

    private fun readInput(inputFileName: String): Pair<Array<CharArray>, Point> {
        val input = getInputLines(inputFileName)

        val map = mutableListOf<CharArray>()
        var start: Point? = null

        for ((i, line) in input.withIndex()) {
            val array = CharArray(line.length)
            for ((j, c) in line.withIndex()) {
                array[j] = c
                if (c == START) start = Point(j, i)
            }
            map += array
        }

        return map.toTypedArray() to start!!
    }
}