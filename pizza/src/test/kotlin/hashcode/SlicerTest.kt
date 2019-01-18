package hashcode

import org.junit.Assert.*
import org.junit.Test

class SlicerTest {


    fun tester(startX: Int, startY: Int, down: Int, right: Int, expectedResult: ArrayList<Point>) {
        val points = Slicer.getCordinatesInsideRectangle(startX, startY, down, right)
        assertTrue(expectedResult.size == points.size)
        expectedResult.forEach { expPoint ->
            assertTrue(points.any { it.x == expPoint.x && it.y == expPoint.y })
        }

    }

    @Test
    fun slicer_getCoordinatesInsideRectangle1() {
        val expectedResult = arrayListOf(Point(0, 0), Point(1, 0), Point(0, 1), Point(1, 1))
        tester(0, 0, 1, 1, expectedResult)
    }

    @Test
    fun slicer_getCoordinatesInsideRectangle2() {
        val expectedResult = arrayListOf(Point(0, 0), Point(1, 0), Point(0, 2), Point(0, 1), Point(1, 1), Point(1, 2))
        tester(0, 0, 1, 2, expectedResult)
    }

    @Test
    fun slicer_getCoordinatesInsideRectangle3() {
        val expectedResult = arrayListOf(
            Point(1, 1), Point(1, 2), Point(1, 3),
            Point(2, 1), Point(2, 2), Point(2, 3),
            Point(3, 1), Point(3, 2), Point(3, 3)

        )
        tester(1, 1, 2, 2, expectedResult)
    }



}