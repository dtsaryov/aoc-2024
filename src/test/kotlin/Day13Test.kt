import kotlin.test.Test
import kotlin.test.assertEquals

class Day13Test {

    @Test
    fun testPart1Example1() {
        assertEquals(480, Day13.part1("day13_test_input.txt"))
    }

    @Test
    fun testPart1() {
        assertEquals(35574, Day13.part1("day13_input.txt"))
    }

    @Test
    fun testPart2Example1() {
        assertEquals(875318608908, Day13.part2("day13_test_input.txt"))
    }

    @Test
    fun testPart2() {
        assertEquals(80882098756071, Day13.part2("day13_input.txt"))
    }
}