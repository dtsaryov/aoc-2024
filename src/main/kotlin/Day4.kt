object Day4 {

    fun part1(): Int {
        val matrix = readInput()

        val mas = "XMAS"
        var result = 0

        for (i in matrix.indices) {
            val line = matrix[i]
            for (j in line.indices) {
                if (line[j] == 'X') {
                    for (di in -1..1) {
                        for (dj in -1..1) {
                            if (di != 0 || dj != 0) {
                                var found = true
                                for (l in 1..3) {
                                    val i1 = i + di * l
                                    val j1 = j + dj * l
                                    if (i1 !in 0..matrix.lastIndex ||
                                        j1 !in 0..line.lastIndex ||
                                        matrix[i1][j1] != mas[l]
                                    ) {
                                        found = false
                                        break
                                    }
                                }
                                if (found) result++
                            }
                        }
                    }
                }
            }
        }

        return result
    }

    fun part2(): Int {
        val matrix = readInput()

        val ms = setOf('M', 'S')
        var result = 0

        for (i in 1 until matrix.lastIndex) {
            val line = matrix[i]
            for (j in 1 until line.lastIndex) {
                if (matrix[i][j] == 'A') {
                    val c1 = matrix[i - 1][j - 1]
                    val c2 = matrix[i + 1][j + 1]
                    if (setOf(c1, c2) != ms) continue
                    val c3 = matrix[i + 1][j - 1]
                    val c4 = matrix[i - 1][j + 1]
                    if (setOf(c3, c4) != ms) continue
                    result++
                }
            }
        }

        return result
    }

    @Suppress("UNCHECKED_CAST")
    private fun readInput(): Array<CharArray> {
        val inputLines = getInputLines("day4_input.txt")

        val matrix = arrayOfNulls<CharArray>(inputLines.size)

        for ((i, line) in inputLines.withIndex()) {
            val array = CharArray(line.length)
            matrix[i] = array
            for ((j, ch) in line.withIndex()) {
                array[j] = ch
            }
        }

        return matrix as Array<CharArray>
    }
}