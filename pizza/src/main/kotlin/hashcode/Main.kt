package hashcode

import astar.AStar
import astar.IGoalNode
import astar.ISearchNode


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
    pizzaGoal.toEmptyPizza()
    val stratPoint = PizzaSliccerSearchNode(pizza, Point(0, 0), null)
    val steps = AStar().shortestPath(stratPoint, PizzaSliccerGoalNode(pizzaGoal))
    val result = steps.map { it as PizzaSliccerSearchNode }
    result.forEach {
        printPizza(it.pizza)
        println("---------")
    }

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




