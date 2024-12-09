import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals

class Day9Test {

    @Test
    fun testExample1ToId() {
        assertEquals(
            "0..111....22222",
            Day9.diskMapToId("day9_test_input.txt")
                .joinToString("")
                .replace("-1", "."))
    }

    @Test
    fun testExample1Defragment() {
        assertEquals(
            "022111222......",
            Day9.defragment("day9_test_input.txt")
                .joinToString("")
                .replace("-1", "."))
    }

    @Test
    fun testExample2ToId() {
        assertEquals(
            "00...111...2...333.44.5555.6666.777.888899",
            Day9.diskMapToId("day9_test_input2.txt")
                .joinToString("")
                .replace("-1", "."))
    }

    @Test
    fun testExample2Defragment() {
        assertEquals(
            "0099811188827773336446555566..............",
            Day9.defragment("day9_test_input2.txt")
                .joinToString("")
                .replace("-1", "."))
    }

    @Test
    fun testExample2DefragmentChecksum() {
        assertEquals(
            BigDecimal(1928),
            Day9.getDefragmentChecksum("day9_test_input2.txt"))
    }

    @Test
    fun testDefragmentChecksum() {
        assertEquals(
            BigDecimal(6461289671426),
            Day9.getDefragmentChecksum("day9_input.txt"))
    }
}