import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Test {

    @Test
    fun testExample1() {
        assertEquals(140, Day12.getFencePriceByPerimeter("day12_test_input.txt"))
    }

    @Test
    fun testExample2() {
        assertEquals(772, Day12.getFencePriceByPerimeter("day12_test_input2.txt"))
    }

    @Test
    fun testExample3() {
        assertEquals(1930, Day12.getFencePriceByPerimeter("day12_test_input3.txt"))
    }

    @Test
    fun testPart1() {
        assertEquals(1374934, Day12.getFencePriceByPerimeter("day12_input.txt"))
    }

    @Test
    fun testExample1Part2() {
        assertEquals(80, Day12.getFencePriceBySides("day12_test_input.txt"))
    }

    @Test
    fun testExample2Part2() {
        assertEquals(436, Day12.getFencePriceBySides("day12_test_input2.txt"))
    }

    @Test
    fun testExample4Part2() {
        assertEquals(236, Day12.getFencePriceBySides("day12_test_input4.txt"))
    }

    @Test
    fun testExample5Part2() {
        assertEquals(368, Day12.getFencePriceBySides("day12_test_input5.txt"))
    }

    @Test
    fun testExample6Part2() {
        assertEquals(64, Day12.getFencePriceBySides("day12_test_input6.txt"))
    }

    @Test
    fun testPart2() {
        assertEquals(841078, Day12.getFencePriceBySides("day12_input.txt"))
    }
}