import kotlin.test.Test
import kotlin.test.assertEquals

class Day5Test {

    @Test
    fun testPart1ExampleInput() {
        assertEquals(143, Day5.part1("day5_test_input.txt"))
    }

    @Test
    fun testPart2ExampleInput() {
        assertEquals(123, Day5.part2("day5_test_input.txt"))
    }

    @Test
    fun testPart1() {
        assertEquals(6267, Day5.part1("day5_input.txt"))
    }

    @Test
    fun testPart2() {
        assertEquals(5184, Day5.part2("day5_input.txt"))
    }
}