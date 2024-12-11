import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {

    @Test
    fun testExample1() {
        assertEquals(55312L, Day11.countStones("day11_test_input.txt", 25))
    }

    @Test
    fun testPart1() {
        assertEquals(197357L, Day11.countStones("day11_input.txt", 25))
    }

    @Test
    fun testPart2() {
        assertEquals(234568186890978L, Day11.countStones("day11_input.txt", 75))
    }
}