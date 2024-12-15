import kotlin.test.Test
import kotlin.test.assertEquals

class Day15Test {

    @Test
    fun testPart1Example1() {
        assertEquals(2028, Day15.part1("day15_test_input.txt"))
    }

    @Test
    fun testPart1Example2() {
        assertEquals(10092, Day15.part1("day15_test_input2.txt"))
    }

    @Test
    fun testPart1() {
        assertEquals(1492518, Day15.part1("day15_input.txt"))
    }
}