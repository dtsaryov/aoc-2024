import kotlin.test.Test
import kotlin.test.assertEquals

class Day19Test {

    @Test
    fun testPart1Example1() {
        assertEquals(6, Day19.part1("day19_test_input.txt"))
    }

    @Test
    fun testPart1() {
        assertEquals(342, Day19.part1("day19_input.txt"))
    }

    @Test
    fun testPart2Example1() {
        assertEquals(16, Day19.part2("day19_test_input.txt"))
    }

    @Test
    fun testPart2() {
        assertEquals(891192814474630, Day19.part2("day19_input.txt"))
    }
}