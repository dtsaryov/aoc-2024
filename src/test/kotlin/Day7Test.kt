import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals

class Day7Test {

    @Test
    fun testPart1ExampleInput() {
        assertEquals(BigDecimal(3749), Day7.part1("day7_test_input.txt"))
    }

    @Test
    fun testPart2ExampleInput() {
        assertEquals(BigDecimal(11387), Day7.part2("day7_test_input.txt"))
    }

    @Test
    fun testPart1() {
        assertEquals(BigDecimal(4122618559853), Day7.part1("day7_input.txt"))
    }

    @Test
    fun testPart2() {
        assertEquals(BigDecimal(227615740238334), Day7.part2("day7_input.txt"))
    }
}