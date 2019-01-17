package hashcode

import java.lang.Math.ceil
import kotlin.math.min


data class Ingredient(val tCnt: Int, val mCnt: Int, val sCnt: Int)


data class rectangle(private val str: String) {
    val rows: Int
    val column: Int

    init {
        val parts = str.split(",")
        rows = parts[0].toInt()
        column = parts[1].toInt()
    }
}

class Slicer {
    companion object {
        fun slice(pizza: Pizza) {
            val (config, plate) = pizza
            val (rows, colums, minIngredient, maxCellsPerSlice) = config
            val (tCnt, mCnt, sCnt) = countIngredient(plate)
            val minCellsToSolve = ceil(colums * rows / maxCellsPerSlice.toDouble())
            val minTopins = min(tCnt, mCnt)
            val possibleRectengales = generatePossibleRectangle(minIngredient, maxCellsPerSlice, rows, colums)
//            solve(pizza, 0, 0, maxCellsPerSlice, minIngredient, possibleRectengales)

            //maxCellsPerSlice Higher and Minimum
        }

        private fun generatePossibleRectangle(
            minIngredient: Int,
            maxCellsPerSlice: Int,
            rows: Int,
            column: Int
        ): List<rectangle> {
            val result = HashSet<String>()
            for (i in minIngredient..maxCellsPerSlice)
                for (j in 1..i) {
                    if (i * j > maxCellsPerSlice) break
                    val rectangle = "$i,$j"
                    result.add(rectangle)
                    result.add(rectangle.reversed())
                }

            var rectangles = result.map { it -> rectangle(it) }
            return rectangles.filter { it.rows <= rows && it.column <= column }
        }


        private fun solve(
            pizza: Pizza,
            i: Int,
            i1: Int,
            maxCellsPerSlice: Int,
            minIngredient: Int,
            possibleRectengales: HashSet<String>
        ) {


        }


        private fun countIngredient(pizza: ArrayList<Array<PARTS>>): Ingredient {
            var tCnt = 0
            var mCnt = 0
            var sCnt = 0
            pizza.forEach { tops ->
                tops.forEach { top ->
                    when (top) {
                        PARTS.TOMATO -> tCnt++
                        PARTS.MUSHROOM -> mCnt++
                        PARTS.SELECTED -> sCnt++
                    }
                }
            }
            return Ingredient(tCnt, mCnt, sCnt)

        }
    }
}