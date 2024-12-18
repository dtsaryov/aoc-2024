import kotlin.test.Test
import kotlin.test.assertEquals

class Day18Test {

    @Test
    fun testPart1Example1() {
        assertEquals(22, Day18.part1("day18_test_input.txt", 12, 7, 7))
    }

    @Test
    fun testPart2Example1() {
        assertEquals(6 to 1, Day18.part2("day18_test_input.txt", 7, 7))
    }

    @Test
    fun testPart1() {
        assertEquals(506, Day18.part1("day18_input.txt", 1024, 71, 71))
    }

    @Test
    fun testPart2() {
        assertEquals(62 to 6, Day18.part2("day18_input.txt", 71, 71))
    }
}