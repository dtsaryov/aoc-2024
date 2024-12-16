import kotlin.test.Test
import kotlin.test.assertEquals

class Day16Test {

    @Test
    fun testPart1Example1() {
        assertEquals(7036, Day16.part1("day16_test_input.txt"))
    }

    @Test
    fun testPart1Example2() {
        assertEquals(11048, Day16.part1("day16_test_input2.txt"))
    }

    @Test
    fun testPart1() {
        assertEquals(65436, Day16.part1("day16_input.txt"))
    }
}