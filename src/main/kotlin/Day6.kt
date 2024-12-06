object Day6 {

    fun part1(inputFileName: String): Int {
        val (field, guard) = readField(inputFileName)

        val visited = mutableSetOf<Pair<Int, Int>>()

        while (guard.canMove(field)) {
            visited += guard.move(field)
        }

        return visited.size
    }

    fun part2(inputFileName: String): Int {
        var (field, guard) = readField(inputFileName)

        val originalGuard = guard.copy()

        var variants = 0

        var visited: MutableMap<Pair<Int, Int>, Int>

        for (i in 0..field.lastIndex) {
            for (j in 0..field[0].lastIndex) {

                // reset
                val original = field[i][j]
                field[i][j] = false
                guard = originalGuard.copy()
                visited = mutableMapOf()

                while (guard.canMove(field)) {
                    val (x, y) = guard.move(field)

                    val count = visited.compute(x to y) { _, v -> (v ?: 0) + 1 }

                    if (count == 20) { // MAGIC CONSTANT
                        variants++
                        break
                    }
                }

                field[i][j] = original
            }
        }

        return variants
    }

    private fun readField(inputFileName: String): Pair<Array<Array<Boolean>>, Guard> {
        val fieldList = mutableListOf<Array<Boolean>>()
        var guard: Guard? = null
        getInputLines(inputFileName).withIndex().forEach { (lineIdx, line) ->
            val lineArray = arrayOfNulls<Boolean>(line.length)
            for ((colIdx, c) in line.withIndex()) when (c) {
                '.' -> {
                    lineArray[colIdx] = true
                }

                '#' -> {
                    lineArray[colIdx] = false
                }

                '^' -> {
                    lineArray[colIdx] = true
                    guard = Guard(colIdx, lineIdx, 0, -1)
                }

                else -> {
                    error("")
                }
            }
            fieldList += lineArray.requireNoNulls()
        }
        return fieldList.toTypedArray() to guard!!
    }

    private data class Guard(
        var x: Int,
        var y: Int,
        var directionX: Int,
        var directionY: Int,
    ) {

        fun canMove(field: Array<Array<Boolean>>): Boolean {
            val nextX = x + directionX
            val nextY = y + directionY
            return nextX in 0..field.lastIndex &&
                    nextY in 0..field[0].lastIndex
        }

        fun move(field: Array<Array<Boolean>>): Pair<Int, Int> {
            val nextX = x + directionX
            val nextY = y + directionY

            if (!field[nextY][nextX]) {
                turn()
                return x to y
            }

            x = nextX
            y = nextY

            return x to y
        }

        private fun turn() {
            val (newDirX, newDirY) = when (directionX to directionY) {
                0 to -1 -> 1 to 0
                1 to 0 -> 0 to 1
                0 to 1 -> (-1) to 0
                -1 to 0 -> 0 to -1
                else -> error("")
            }
            directionX = newDirX
            directionY = newDirY
        }
    }
}