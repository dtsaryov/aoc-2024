import kotlin.test.Test
import kotlin.test.assertEquals

class Day8Test {

    @Test
    fun testPart1ExampleInput() {
        assertEquals(14, Day8.part1("day8_test_input.txt"))
    }

    @Test
    fun testPart2ExampleInput() {
        assertEquals(34, Day8.part2("day8_test_input.txt"))
    }

    @Test
    fun testPart1() {
        assertEquals(336, Day8.part1("day8_input.txt"))
    }

    @Test
    fun testPart2() {
        assertEquals(1131, Day8.part2("day8_input.txt"))
    }
}