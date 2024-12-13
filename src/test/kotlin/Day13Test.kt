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
}