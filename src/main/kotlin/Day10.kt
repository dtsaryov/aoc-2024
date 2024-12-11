object Day10 {

    fun getTrailHeadScoreSum(inputFileName: String): Int {
        val (grid, trailHeads) = readInput(inputFileName)

        return trailHeads.sumOf { (i, j) ->
            searchPath(grid, i to j, 0, mutableSetOf())
        }
    }

    fun getTrailRating(inputFileName: String): Int {
        val (grid, trailHeads) = readInput(inputFileName)

        return trailHeads.sumOf { (i, j) ->
            searchPath2(grid, i to j, 0)
        }
    }

    private fun searchPath(
        grid: Array<IntArray>,
        pos: Pair<Int, Int>,
        level: Int,
        visited: MutableSet<Pair<Int, Int>>
    ): Int {
        val (i, j) = pos
        if (grid[i][j] == 9) {
            return if (visited.add(i to j)) 1 else 0
        }

        return getNextSteps(pos)
            .filter {
                checkPos(it.first, it.second, grid) && grid[it.first][it.second] == level + 1
            }.sumOf { searchPath(grid, it, level + 1, visited) }
    }

    private fun searchPath2(
        grid: Array<IntArray>,
        pos: Pair<Int, Int>,
        level: Int
    ): Int {
        val (i, j) = pos
        if (grid[i][j] == 9)
            return 1

        return getNextSteps(pos)
            .filter {
                checkPos(it.first, it.second, grid) && grid[it.first][it.second] == level + 1
            }.sumOf { searchPath2(grid, it, level + 1) }
    }

    private fun checkPos(i: Int, j: Int, grid: Array<IntArray>) = i in grid.indices && j in grid[0].indices

    private fun getNextSteps(pos: Pair<Int, Int>): List<Pair<Int, Int>> {
        val (i, j) = pos
        return listOf(
            Pair(i - 1, j),
            Pair(i, j + 1),
            Pair(i + 1, j),
            Pair(i, j - 1)
        )
    }

    private fun readInput(inputFileName: String): Pair<Array<IntArray>, List<Pair<Int, Int>>> {
        val input = getInputLines(inputFileName)

        val grid = Array(input.size) { IntArray(input[it].length) }
        val trailHeads = mutableListOf<Pair<Int, Int>>()

        for ((i, line) in input.withIndex()) {
            for ((j, c) in line.withIndex()) {
                val d = try {
                    c.digitToInt()
                } catch (e: Exception) {
                    -1
                }
                grid[i][j] = d
                if (d == 0) trailHeads += i to j
            }
        }

        return grid to trailHeads
    }
}