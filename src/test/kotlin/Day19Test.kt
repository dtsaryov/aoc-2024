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
}