import java.util.*

private const val WALL = '#'

private val DIRECTIONS: Pair<IntArray, IntArray> = Pair(
    intArrayOf(0, 0, 1, -1), // Y
    intArrayOf(1, -1, 0, 0)  // X
)

object Day18 {

    fun part1(inputFileName: String, bytes: Int, m: Int, n: Int): Int {
        val maze = Array(n) { CharArray(m) }
        getInputLines(inputFileName).take(bytes).forEach { line ->
            val (x, y) = line.split(',')
            maze[y.toInt()][x.toInt()] = WALL
        }

        return searchPath(maze, Point(0, 0))
    }

    private fun searchPath(maze: Array<CharArray>, start: Point): Int {
        val (yDirections, xDirections) = DIRECTIONS

        val (startY, startX) = start

        val n = maze.size
        val m = maze[0].size

        val costs = Array(m) { Array(n) { IntArray(4) { Int.MAX_VALUE } } }
        // 3d matrix of locations and directions
        val visited = Array(m) { Array(n) { false } }

        val queue = PriorityQueue<Path>()
        fun enq(i: Int, j: Int, d: Int, c: Int) {
            if (costs[i][j][d] <= c || maze[i][j] == WALL) return

            costs[i][j][d] = c

            queue.add(Path(j, i, d, c))
        }

        enq(startX, startY, 0, 0)

        while (true) {
            val (j, i, _, cost) = queue.remove()
                ?: error("No more paths")

            if (visited[i][j]) continue
            visited[i][j] = true

            if (i == n - 1 && j == m - 1) {
                return cost
            }

            for (di in 0..3) {
                val newD = di % 4

                val newY = i + yDirections[newD]
                val newX = j + xDirections[newD]
                if (newX !in 0..<m || newY !in 0..<n) continue

                enq(newY, newX, newD, cost + 1)
            }
        }
    }
}