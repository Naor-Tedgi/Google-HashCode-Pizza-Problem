package hashcode

import org.junit.Test

import org.junit.Assert.*

class PizzaTest {

    fun createSmallPizza(): Pizza {
        val input =
            "/Users/naortedgi/Desktop/Google-HashCode-Pizza-Problem/pizza/src/main/kotlin/resources/a_example.in"
        return PizzaGenerator.create(input)
    }

    @Test
    fun copy() {
        val p1 = createSmallPizza()
        p1.plate[0][0] = PARTS.SELECTED
        val p2 = p1.copy()
        assertTrue(p2 == p1)
    }

    @Test
    fun toEmptyPizza() {
        val p1 = createSmallPizza()
        val p2 = p1.copy()
        p1.toEmptyPizza()
        assertTrue(p2 != p1)
        p1.plate.forEach { row ->
            row.forEach { sqr ->
                assertTrue(sqr == PARTS.SELECTED)

            }
        }
    }

    @Test
    fun markSlice1() {
        val p1=createSmallPizza()
        p1.markSlice(Rectangle("1,1"),0,0)
        assertTrue(p1.plate[0][0]==PARTS.SELECTED)
        assertTrue(p1.plate[1][0]==PARTS.SELECTED)
        assertTrue(p1.plate[0][1]==PARTS.SELECTED)
        assertTrue(p1.plate[1][1]==PARTS.SELECTED)
    }

    @Test
    fun markSlice2() {
        val p1=createSmallPizza()
        p1.markSlice(Rectangle("1,1"),1,1)
        assertTrue(p1.plate[1][1]==PARTS.SELECTED)
        assertTrue(p1.plate[1][2]==PARTS.SELECTED)
        assertTrue(p1.plate[2][1]==PARTS.SELECTED)
        assertTrue(p1.plate[2][2]==PARTS.SELECTED)
    }


    @Test
    fun copy1() {
    }
}