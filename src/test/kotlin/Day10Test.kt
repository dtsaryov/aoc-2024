import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {

    @Test
    fun testExample1() {
        assertEquals(2, Day10.getTrailHeadScoreSum("day10_test_input.txt"))
    }

    @Test
    fun testExample2() {
        assertEquals(4, Day10.getTrailHeadScoreSum("day10_test_input2.txt"))
    }

    @Test
    fun testExample3() {
        assertEquals(3, Day10.getTrailHeadScoreSum("day10_test_input3.txt"))
    }

    @Test
    fun testExample4() {
        assertEquals(36, Day10.getTrailHeadScoreSum("day10_test_input4.txt"))
    }

    @Test
    fun testExample1Rating() {
        assertEquals(3, Day10.getTrailRating("day10_test_input5.txt"))
    }

    @Test
    fun testExample2Rating() {
        assertEquals(13, Day10.getTrailRating("day10_test_input2.txt"))
    }

    @Test
    fun testExample3Rating() {
        assertEquals(227, Day10.getTrailRating("day10_test_input6.txt"))
    }

    @Test
    fun testExample4Rating() {
        assertEquals(81, Day10.getTrailRating("day10_test_input7.txt"))
    }

    @Test
    fun testPart1() {
        assertEquals(820, Day10.getTrailHeadScoreSum("day10_input.txt"))
    }

    @Test
    fun testPart2() {
        assertEquals(1786, Day10.getTrailRating("day10_input.txt"))
    }
}