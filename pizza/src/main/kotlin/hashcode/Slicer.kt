package hashcode

import java.lang.Math.ceil
import kotlin.math.min


data class Ingredient(val tCnt: Int, val mCnt: Int, val sCnt: Int)

class Slicer {
    companion object {
        fun slice(pizza: Pizza) {
            val (config, plate) = pizza
            val (rows, colums, minIngredient, maxCellsPerSlice) = config
            val (tCnt, mCnt, sCnt) = countIngredient(plate)
            val minCellsToSolve = ceil(colums * rows / maxCellsPerSlice.toDouble())
            val minTopins = min(tCnt,mCnt)

            solve(pizza,0,0)

            //maxCellsPerSlice Higher and Minimum
        }

        private fun solve(pizza: Pizza, i: Int, i1: Int) {

        }

        private fun getMaxSlices(tCnt: Int, mCnt: Int, maxCellsPerSlice: Int, minIngredient: Int): Int {
            return 0
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