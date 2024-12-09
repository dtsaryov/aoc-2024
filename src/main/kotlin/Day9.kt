import java.math.BigDecimal

private val EMPTY_SPACE = BigDecimal(-1)

object Day9 {

    // part 1
    fun getFragmentedChecksum(inputFileName: String): BigDecimal {
        var result = BigDecimal.ZERO

        val fragmented = fragment(inputFileName)
        for ((i, c) in fragmented.withIndex()) {
            if (c != EMPTY_SPACE) {
                val num = c * BigDecimal(i)
                result += num
            }
        }

        return result
    }

    // part 1
    fun fragment(inputFileName: String): List<BigDecimal> {
        val id = diskMapToId(inputFileName)

        val result = id.toMutableList()

        for (idx in id.lastIndex downTo 0) {
            val num = result[idx]
            if (num == EMPTY_SPACE) continue

            val spaceIdx = result.indexOf(EMPTY_SPACE)

            // all files moved
            if (spaceIdx > idx) break

            // move file
            result[spaceIdx] = num
            result[idx] = EMPTY_SPACE
        }

        return result
    }

    // part 2
    fun getDefragmentedChecksum(inputFileName: String): BigDecimal {
        var result = BigDecimal.ZERO

        val defragmented = defragment(inputFileName)

        for ((i, c) in defragmented.withIndex()) {
            if (c != EMPTY_SPACE) {
                val num = c * BigDecimal(i)
                result += num
            }
        }

        return result
    }

    // part 2
    private fun defragment(inputFileName: String): List<BigDecimal> {
        val id = diskMapToId(inputFileName)

        val result = id.toMutableList()

        var idx = id.lastIndex
        while (idx >= 0) {
            val num = result[idx]
            if (num == EMPTY_SPACE) {
                idx--
                continue
            }

            // search for the next block
            var chunkStart = idx
            while (chunkStart >= 0 && result[chunkStart] == num) {
                chunkStart--
            }
            val chunkSize = idx - chunkStart

            // check for space
            val spaceIdx = findEmptySpace(result, chunkSize, chunkStart + 1)
            if (spaceIdx != -1) {
                // move files
                for (i in 0 until chunkSize) {
                    result[spaceIdx + i] = result[chunkStart + 1 + i]
                    result[chunkStart + 1 + i] = EMPTY_SPACE
                }
            }

            idx = chunkStart
        }

        return result
    }

    private fun findEmptySpace(data: List<BigDecimal>, chunkSize: Int, chunkStart: Int): Int {
        var spaceIdx = -1
        var emptySpace = 0
        for (i in 0 until chunkStart) {
            if (data[i] == EMPTY_SPACE) {
                if (spaceIdx == -1) spaceIdx = i
                emptySpace++
            } else {
                spaceIdx = -1
                emptySpace = 0
            }

            if (emptySpace == chunkSize) {
                return spaceIdx
            }
        }
        return -1
    }

    fun diskMapToId(inputFileName: String): List<BigDecimal> {
        var data = true

        var id = BigDecimal.ZERO

        val input = getInputText(inputFileName)
        val result = mutableListOf<BigDecimal>()

        for (c in input) {
            val blocks = c.digitToInt()
            if (data) {
                repeat(0.until(blocks).count()) {
                    result += id
                }
                data = false
            } else {
                repeat(0.until(blocks).count()) {
                    result += BigDecimal(-1)
                }
                data = true
                id++
            }
        }

        return result
    }
}