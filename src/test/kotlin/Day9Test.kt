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
    fun testExample1Fragment() {
        assertEquals(
            "022111222......",
            Day9.fragment("day9_test_input.txt")
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
    fun testExample2Fragment() {
        assertEquals(
            "0099811188827773336446555566..............",
            Day9.fragment("day9_test_input2.txt")
                .joinToString("")
                .replace("-1", "."))
    }

    @Test
    fun testExample2FragmentChecksum() {
        assertEquals(
            BigDecimal(1928),
            Day9.getFragmentedChecksum("day9_test_input2.txt"))
    }

    @Test
    fun testExample2DefragmentedChecksum() {
        assertEquals(
            BigDecimal(2858),
            Day9.getDefragmentedChecksum("day9_test_input2.txt"))
    }

    @Test
    fun testExample3DefragmentedChecksum() {
        assertEquals(
            BigDecimal(4),
            Day9.getDefragmentedChecksum("day9_test_input3.txt"))
    }

    @Test
    fun testExample4DefragmentedChecksum() {
        assertEquals(
            BigDecimal(15),
            Day9.getDefragmentedChecksum("day9_test_input4.txt"))
    }

    @Test
    fun testFragmentedChecksum() {
        assertEquals(
            BigDecimal(6461289671426),
            Day9.getFragmentedChecksum("day9_input.txt"))
    }

    @Test
    fun testDefragmentedChecksum() {
        assertEquals(
            BigDecimal(6488291456470),
            Day9.getDefragmentedChecksum("day9_input.txt"))
    }
}