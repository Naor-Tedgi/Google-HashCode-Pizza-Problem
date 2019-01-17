package hashcode

import java.lang.Math.ceil
import java.lang.Math.min


data class Ingredient(val tCnt: Int, val mCnt: Int, val sCnt: Int)

data class RectangleSelected(val rectangle: Rectangle, val tomatoCnt: Int, val mushroomCat: Int, val sliceSize: Int)
data class Rectangle(private val str: String) {
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
            val (rows, column, minIngredient, maxCellsPerSlice) = config
            val (tCnt, mCnt, sCnt) = countIngredient(plate)
            val minCellsToSolve = ceil(column * rows / maxCellsPerSlice.toDouble())
            val minTaping = min(tCnt, mCnt)
            minCellsToSolve + minTaping + sCnt
            val possibleRectangles = generatePossibleRectangle(minIngredient, maxCellsPerSlice, rows, column)
            solve(pizza, 2, 2, possibleRectangles, config)

            //maxCellsPerSlice Higher and Minimum
        }

        private fun solve(pizza: Pizza, i: Int, j: Int, possibleRectangles: List<Rectangle>, config: PizzaConfig) {
            val validSlicesBySize =
                getAllValidSlicesFromCurrentPointByPizzaPlateSize(i, j, possibleRectangles, config)
            val validSlicesNotContainingSelected =
                getAllValidSlicesNotContainingSelected(pizza, i, j, validSlicesBySize)
            val validSlicesContainingMinTaping =
                getAllValidSlicesContainingMinTaping(
                    pizza,
                    i,
                    j,
                    config.minIngredient,
                    validSlicesNotContainingSelected
                )
            validSlicesContainingMinTaping.trimToSize()

        }

        private fun getAllValidSlicesContainingMinTaping(
            pizza: Pizza,
            i: Int,
            j: Int,
            minIngredient: Int,
            validSlicesNotContainingSelected: ArrayList<Rectangle>
        ): ArrayList<RectangleSelected> {

            val result = ArrayList<RectangleSelected>()

            validSlicesNotContainingSelected.forEach { rec ->
                var tCnt = 0
                var mCnt = 0
                for (iInd in 0 until rec.rows)
                    for (jInd in 0 until rec.column)
                        when (pizza.plate[i + iInd][j + jInd]) {
                            PARTS.TOMATO -> tCnt++
                            PARTS.MUSHROOM -> mCnt++
                            else -> {

                            }
                        }
                if (tCnt > minIngredient && mCnt > minIngredient) {
                    result.add(RectangleSelected(rec, tCnt, mCnt, rec.rows * rec.column))
                }


            }
            return result

        }


        private fun getAllValidSlicesNotContainingSelected(
            pizza: Pizza,
            i: Int,
            j: Int,
            validSlicesBySize: ArrayList<Rectangle>
        ): ArrayList<Rectangle> {
            val result = ArrayList<Rectangle>()
            validSlicesBySize.forEach { rec ->
                var insert = true
                for (iInd in 0 until rec.rows)
                    for (jInd in 0 until rec.column)
                        if (pizza.plate[i + iInd][j + jInd] == PARTS.SELECTED) {
                            insert = false
                            break
                        }

                if (insert) result.add(rec)

            }
            return result

        }

        private fun generatePossibleRectangle(
            minIngredient: Int,
            maxCellsPerSlice: Int,
            rows: Int,
            column: Int
        ): List<Rectangle> {
            val result = HashSet<String>()
            for (i in minIngredient..maxCellsPerSlice)
                for (j in 1..i) {
                    if (i * j > maxCellsPerSlice) break
                    val rectangle = "$i,$j"
                    result.add(rectangle)
                    result.add(rectangle.reversed())
                }
            return result.map { it -> Rectangle(it) }
                .filter { it.rows <= rows && it.column <= column }
        }


        private fun getAllValidSlicesFromCurrentPointByPizzaPlateSize(
            i: Int,
            j: Int,
            possibleRectangles: List<Rectangle>,
            config: PizzaConfig
        ): ArrayList<Rectangle> {
            val result = ArrayList<Rectangle>()

            possibleRectangles.forEach {
                if (i + it.rows <= config.rows && j + it.column <= config.colums)
                    result.add(it)

            }

            return result
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