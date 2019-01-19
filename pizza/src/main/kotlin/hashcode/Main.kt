package hashcode

import astar.AStar
import astar.ISearchNode
import kotlin.system.measureTimeMillis


lateinit var path: String

enum class INPUT(val PATH: String) {
    EXAMPLE("$path/a_example.in"),
    SMALL("$path/b_small.in"),
    MEDIUM("$path/c_medium.in"),
    BIG("$path/d_big.in");
}


fun main(args: Array<String>) {
    path = getResourcesDir()
    val input = INPUT.SMALL.PATH
    val pizza = PizzaGenerator.create(input)
    val pizzaGoal = pizza.copy()
        val x = measureTimeMillis {
            pizzaGoal.toEmptyPizza()
            val startPoint = PizzaSliccerSearchNode(pizza, Point(0, 0), null)
            val steps = AStar().shortestPath(startPoint, PizzaSliccerGoalNode(pizzaGoal))
            val result = steps.map { it as PizzaSliccerSearchNode }

//            result.forEach {
//                printPizza(it.pizza)
//                println("---------")
//            }
//            println("---------")
////
//            println(calcScore(steps))
        }
    println(x)
    }

fun calcScore(results: ArrayList<ISearchNode>): Int {
    var startPoint = results.get(0) as PizzaSliccerSearchNode
    var result = 0
    results.drop(1).forEach { step ->
        step as PizzaSliccerSearchNode
        result += pizzaDiff(step.pizza.plate, startPoint.pizza.plate)
        startPoint = step
    }

    return result
}

fun pizzaDiff(plateA: ArrayList<Array<PARTS>>, plateB: ArrayList<Array<PARTS>>): Int {
    var result = 0
    plateA.forEachIndexed { indI, row ->
        row.forEachIndexed { indJ, value ->
            if (value != plateB[indI][indJ]) result++
        }
    }
    return result
}

fun printPizza(pizza: Pizza) {
    pizza.plate.forEach {
        it.forEach {
            when (it) {
                PARTS.TOMATO -> print("T")
                PARTS.MUSHROOM -> print("M")
                PARTS.SELECTED -> print("S")
            }
        }
        println()
    }

}


private fun getResourcesDir() =
    arrayListOf<String>(System.getProperty("user.dir"), "src", "main", "kotlin", "resources").joinToString("/")


//16
//42
