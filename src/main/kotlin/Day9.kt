import java.math.BigDecimal

private val EMPTY_SPACE = BigDecimal(-1)

object Day9 {

    fun getDefragmentChecksum(inputFileName: String): BigDecimal {
        var result = BigDecimal.ZERO

        val defragment = defragment(inputFileName)
        for ((i, c) in defragment.withIndex()) {
            if (c != EMPTY_SPACE) {
                val num = c * BigDecimal(i)
                result += num
            }
        }

        return result
    }

    fun defragment(inputFileName: String): List<BigDecimal> {
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