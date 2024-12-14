import kotlin.test.Test
import kotlin.test.assertEquals

class Day14Test {

    @Test
    fun testPart1Example1() {
        assertEquals(12, Day14.getSafetyFactor("day14_test_input.txt", 7, 11))
    }

    @Test
    fun testPart1() {
        assertEquals(218619324, Day14.getSafetyFactor("day14_input.txt", 103, 101))
    }
}