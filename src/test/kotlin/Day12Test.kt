import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Test {

    @Test
    fun testExample1() {
        assertEquals(140, Day12.getFencePrice("day12_test_input.txt"))
    }

    @Test
    fun testExample2() {
        assertEquals(772, Day12.getFencePrice("day12_test_input2.txt"))
    }

    @Test
    fun testExample3() {
        assertEquals(1930, Day12.getFencePrice("day12_test_input3.txt"))
    }

    @Test
    fun testPart1() {
        assertEquals(-1, Day12.getFencePrice("day12_input.txt"))
    }
}