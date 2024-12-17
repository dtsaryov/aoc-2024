import kotlin.test.Test
import kotlin.test.assertEquals

class Day17Test {

    @Test
    fun testPart1Example1() {
        assertEquals("0,1,2", Day17.part1("day17_test_input.txt"))
    }

    @Test
    fun testPart1Example2() {
        assertEquals("4,2,5,6,7,7,7,7,3,1,0", Day17.part1("day17_test_input2.txt"))
    }

    @Test
    fun testPart1() {
        assertEquals("7,3,0,5,7,1,4,0,5", Day17.part1("day17_input.txt"))
    }
}