import kotlin.test.Test
import kotlin.test.assertEquals

class Day6Test {

    @Test
    fun testPart1ExampleInput() {
        assertEquals(41, Day6.part1("day6_test_input.txt"))
    }

    @Test
    fun testPart2ExampleInput() {
        assertEquals(6, Day6.part2("day6_test_input.txt"))
    }

    @Test
    fun testPart1() {
        assertEquals(5131, Day6.part1("day6_input.txt"))
    }

    @Test
    fun testPart2() {
        assertEquals(1784, Day6.part2("day6_input.txt"))
    }
}